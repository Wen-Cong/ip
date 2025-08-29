import bot.Bot;

public class Main {
    public static void main(String[] args) {
        // Init a bot to begin program
        Bot bot = new Bot("Lovely", "data/taskData.txt");
        bot.run();
    }
}
