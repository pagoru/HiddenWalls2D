package es.pagoru.HiddenWalls2D;

import java.util.Scanner;

/**
 * Created by pablo on 21/9/16.
 */
public class Game {

    public Game(){
        loop();
    }

    private Map map = new Map(Map.DEFAULT_MAP);
    private Scanner scanner = new Scanner(System.in);

    public void loop(){

        String infoText = "Per mouret, introdueix: ↑'w', ↓'s', ←'a' o →'d'";

        while(true){

            printWindow(infoText, 0);

            String key = scanner.nextLine();
            int x = 0;
            int y = 0;
            if(key.equalsIgnoreCase("w")){
                y--;//Invertit degut a que la impresió en pantalla comença desde adalt
            } else if(key.equalsIgnoreCase("s")){
                y++;
            } else if(key.equalsIgnoreCase("a")){
                x--;
            } else if(key.equalsIgnoreCase("d")){
                x++;
            }

            if(x == 0 && y == 0){
                return;
            }
            Vector2Di newPos = new Vector2Di(x, y);

            Map.MapType mapType = map.getPosition(map.getPlayer().getPosition().copy().add(newPos));
            System.out.println(mapType.name());
            if(mapType.equals(Map.MapType.ROAD) || mapType.equals(Map.MapType.DESTROYED_WALL)){
                map.addPlayerPosition(newPos);
                infoText = "";
                continue;
            } else if(mapType.equals(Map.MapType.BOMB)){
                map.addPlayerPosition(newPos);
                map.getPlayer().addHearts(-1);
                map.setPosition(map.getPlayer().getPosition(), Map.MapType.ROAD);
                if(map.getPlayer().getHearts() != 0){
                    infoText = "¡BOOOM! (☠)";
                    continue;
                }
                printWindow("¡Gracies per jugar! :)", 1);
                break;
            } else if(mapType.equals(Map.MapType.HEARTS)){
                map.addPlayerPosition(newPos);
                map.getPlayer().addHearts(1);
                map.setPosition(map.getPlayer().getPosition(), Map.MapType.ROAD);
                infoText = "¡Has recuperat una vida! (❤)";
                continue;
            } else if(mapType.equals(Map.MapType.PICKAXE)){
                map.addPlayerPosition(newPos);
                map.getPlayer().addPickaxe();
                map.setPosition(map.getPlayer().getPosition(), Map.MapType.ROAD);
                infoText = "¡Has aconseguit la piqueta! (ψ)";
                continue;
            } else if(mapType.equals(Map.MapType.WALL)){
                if(map.getPlayer().hasPickaxe()){
                    map.destroyWall(map.getPlayer().getPosition().copy().add(newPos));
                    mapType = map.getPosition(map.getPlayer().getPosition().copy().add(newPos));
                    infoText = "No es poden destruir les parets externes.";
                    if(!mapType.equals(Map.MapType.WALL)){
                        map.getPlayer().addPosition(newPos);
                        infoText = "S'ha destruït una paret.";
                    }
                    continue;
                }
                map.addTrail(map.getPlayer().getPosition().copy().add(newPos));
                infoText = "¡ANOTHER BRICK IN THE WALL! (♫)";
                continue;
            } else if(mapType.equals(Map.MapType.EXIT)){
                printWindow("¡Gracies per jugar! :)", 2);
                break;
            }
            infoText = "No esta permesa aquesta tecla.";
        }


    }

    /**
     * Ha de retornar 58 caracters per tal de completar correctament la pantalla
     * @return
     */
    private String getText(String text){
        int ci = 58 - text.length();
        for (int i = 0; i < ci; i++){
            text += " ";
        }
        return text;
    }

    public void printWindow(String infoText, int status){
        for(int i = 0; i < 10; i++){
            System.out.println();
        }
        System.out.println("╒════════════════════════════════════════════════════════════╕");
        switch (status){
            case 0:
                for(int y = 0; y < Map.HEIGHT; y++){
                    System.out.print("│");
                    for(int x = 0; x < Map.WIDTH; x++){
                        System.out.print(map.getPrintablePosition(new Vector2Di(x, y)).getSymbol() + " ");
                    }
                    System.out.println("│");
                }
                break;
            case 1:
                for(int i = 0; i < 4; i++){
                    System.out.println("│ " + getText("") + " │");
                }
                System.out.println("│                            GAME                            │");
                System.out.println("│                            OVER                            │");
                for(int i = 0; i < 4; i++){
                    System.out.println("│ " + getText("") + " │");
                }
                break;
            case 2:
                for(int i = 0; i < 4; i++){
                    System.out.println("│ " + getText("") + " │");
                }
                System.out.println("│                             YOU                            │");
                System.out.println("│                             WIN                            │");
                for(int i = 0; i < 4; i++){
                    System.out.println("│ " + getText("") + " │");
                }
                break;
        }

        System.out.println("╞════════════════════════════════════════════════════════════╡");
        System.out.println("│ " + getText(infoText) + " │");
        System.out.println("╞══════════════════════════════════╤═════════════════════════╡");
        System.out.println(
                "│ Hidden Walls 2D " + HiddenWalls2D.VERSION
                        + "  by @pagoru │ Jugador: '"
                        + map.getPlayer().getSymbol() + "' "
                        + map.getPlayer().getPrintableHearts() + "│");
        System.out.println("╘══════════════════════════════════╧═════════════════════════╛");
    }

}
