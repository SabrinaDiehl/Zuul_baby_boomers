package  baby_boomers;

/**
 * @author sabrina.diehl
 */

public class Game {
    private final int peso_maximo = 1000;
    private Parser parser;
    private Player player;
    private Item item;
        
    
    public Game() 
    {
        player = new Player(peso_maximo);
        player.setCurrentWeight(0);
        createRooms();
        parser = new Parser();
        
    }

    private void createRooms()
    {
        Room outside, cantina, mercado, laboratorio, secretaria, lanchonete;
        Item bolacha, maca, rabanete, sanduiche, bala, marmita;
      
        bolacha = new Item("bolacha"," bolacha de morango", 250);
		maca = new Item("maca", " faz bem para a saúde", 250);
        rabanete = new Item("rabanete"," muita vitamina", 300);
        sanduiche = new Item("sanduiche"," pão com queijo, presunto e alface", 150);
        bala = new Item("bala"," gruda no dente", 50);
        marmita = new Item("marmita", " feijão, arroz, batata frita e salada", 500); 
        
        // create the rooms
        outside = new Room("fora da entrada principal da universidade", abobora);
        cantina = new Room("em uma cantina", sorvete);
        mercado = new Room("no mercado", Petit_gateau);
        laboratorio = new Room("em um laboratório", biscoitoMagico);
        secretaria = new Room("na secretaria", bolacha);
        lanchonete = new Room("na lanchonete", rabanete);
        
        // initialise room exits
        outside.setExit("cantina", cantina);
        outside.setExit("laboratorio", laboratorio);
        outside.setExit("mercado", mercado); 
        cantina.setExit("inicio", outside);  
        mercado.setExit("inicio", outside);    
        laboratorio.setExit("inicio", outside);
        laboratorio.setExit("secretaria", secretaria);
        laboratorio.setExit("lanchonete", lanchonete); 
        lanchonete.setExit("laboratorio", laboratorio);
        secretaria.setExit("laboratorio", laboratorio);

        player.setCurrentRoom(outside);
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar!!!");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem-vindo ao jogo!");
        System.out.println("Um jogo de aventura!");
        System.out.println("Se você precisar de ajuda, digite 'ajuda'.");
        System.out.println();
        player.printLocationInfo();
        
    }
  
    
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Não sei o que você quer dizer...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ajuda"))
            printHelp();
        else if (commandWord.equals("ir_para"))
            goRoom(command);
        else if (commandWord.equals("sair"))
            wantToQuit = quit(command);
        else if (commandWord.equals("verificar"))
            ver();
        else if (commandWord.equals("comer"))
            eat(command);
        else if (commandWord.equals("voltar"))
            returnRoom(command);
        else if (commandWord.equals("pegar"))
            pegar(command);
        else if (commandWord.equals("soltar"))
            soltar(command);
        else if (commandWord.equals("itens"))
            imprimir();
        return wantToQuit;
    }
    
    public void pegar(Command command) {
         if(!command.hasSecondWord()) {
            System.out.println("Pegar o quê?");
        }else{
            if((player.getCurrentRoom().getItem()) != null){
                 if(command.getSecondWord().equals(player.getCurrentRoom().getItem().getNome())){
                    if((player.getCurrentRoom().getItem().canBePickedUp(player))){
                        System.out.println("Esse item está coletado");
                        player.addItem(player.getCurrentRoom().getItem());
                        player.getCurrentRoom().setItem(null);
                    }
                    else{
                        System.out.println("Peso exedido");
                    }
                 }else{
                    System.out.println("Este item não existe");
                }        
            }else{
                System.out.println("Nesta sala não se encontra nenhum item"); 
        }
    }
}

    public void soltar(Command command) {
        int control = 0;
        if(player.getCurrentRoom().getItem() == null){
            if(!command.hasSecondWord()) {
                System.out.println("Soltar qual item?");
            }else{
                if(!player.getItensPlayer().isEmpty()){
                    for(Item item: player.getItensPlayer()){
                        if( item.getNome().equals(command.getSecondWord())){
                            System.out.println("Você soltou.");
                            player.decrementCurrentWeight(item.getWeight());
                            player.getCurrentRoom().setItem(item);
                        }else{
                            control++;
                            if(control == player.getItensPlayer().size()){
                                System.out.println("Esse item não existe");
                            }
                        }
                    }
                }else{
                    System.out.println("Você não tem nenhum item para soltar");
                }
            }
        }else{
            System.out.println("Você não pode soltar esste item aqui");
        }
        
    }
        
    public void eat(Command command) {
        int control = 0;
        if((player.getCurrentRoom().getItem()) != null){
            if(!command.hasSecondWord()) {
                System.out.println("Comer o que?");
            }else{
                if(command.getSecondWord().equals(player.getCurrentRoom().getItem().getNome())){   
                    if("rabanete".equals(player.getCurrentRoom().getItem().getNome())){
                        player.setCurrentWeight(player.getCurrentWeight() + 500);
                        System.out.println("Você consegue carregar mais 500 quilos");
                        System.out.println("Você comeu e ainda esta com fome.Pegue mais comida");
                        player.getCurrentRoom().setItem(null);
                    }else{
                        System.out.println("Você comeu e ainda esta com fome.Pegue mais comida");
                        player.getCurrentRoom().setItem(null);
                    }
                }else{
                        control++;
                        if(control == player.getItensPlayer().size()){
                            System.out.println("Esse item não existe");
                        }
                }
            }
        }else{
            System.out.println("Não tem nenhum item nesta sala"); 
        }
    }
    
       public void imprimir() {
           System.out.println("Você já coletou: ");
            for(Item item: player.getItensPlayer()){
                System.out.println(item.getLongDescription());
            }
    }
        

    private void printHelp() 
    {
        System.out.println("Você está perdido. Você está alone. Você caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Seus comandos são:");
        System.out.println("   " + parser.getCommandList());
    }
    
    private void returnRoom(Command command) //player
    {
        if (command.hasSecondWord()) {
            System.out.println("Voltar o quê?");
        } else {
            player.returnRoom(command);
        }         
    }
    
    
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Ir para onde?");
            return;
        }
        String direction = command.getSecondWord();
        player.goRoom(direction); 
    }
    
    
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Sair de do quê?");
            return false;
        }
        else {
            return true; 
        }
    }

    private void ver() {
       player.printLocationInfo();
    }
}
