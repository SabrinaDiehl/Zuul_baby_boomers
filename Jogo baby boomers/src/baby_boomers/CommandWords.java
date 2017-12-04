package  baby_boomers;

/**
 *
 * @author sabrina.diehl
 */

public class CommandWords {
    // um array constante que contém todos os comandos válidos
    private static final String[] VALID_COMMANDS = {
        "ir_para", "sair", "ajuda", "examinar", "comer", "voltar", "pegar", "soltar", "itens"
    };


    public CommandWords()
    {
        
    }
    
    public String getCommandList()
    {
        return String.join(" ", VALID_COMMANDS);
    }
 
    public boolean isCommand(String aString)
    {
        for (String command : VALID_COMMANDS) {
            if (command.equals(aString)) {
                return true;
            }
        }
        return false;
    }
}
