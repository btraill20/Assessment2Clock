package clock;

public class Clock {
    
    public static void main(String[] args) {
        Model model = new Model<>(10);
        View view = new View(model);
        model.addObserver(view);
        Controller controller = new Controller(model, view);
    }
}
