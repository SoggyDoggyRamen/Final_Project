public class PickledEggGenerator {
    private int amount;
    private PickledEgg[] pickledEggs;

    public PickledEggGenerator(int amount) {
        this.amount = amount;
        pickledEggs = new PickledEgg[amount];
    }

    public void generate() {
        for (int i = 0; i < amount; i ++) {
            int randX = (int) (Math.random()
            pickledEggs[i] = new PickledEgg()
        }
    }
}
