
public class Human implements  Actiity {
    private static int objCounter = 0;
    private final String name;
    final int maxDistanceRun, maxHeight;

    public Human()
    {
        this.name = "#" + (++objCounter);
        this.maxDistanceRun = (int)(100*Math.random()+1);
        this.maxHeight = (int)(100*Math.random()+1);
    }
    public void take(Obstacle...obstacles)
    {
        for(Obstacle obstacle:obstacles) {
            if(!obstacle.take(this)) {
                return;
            }
        }
        System.out.println("все преп.преодолены " + name);
    }

    @Override
    public int getJumpLimit() {
        return maxHeight;
    }
    @Override
    public int getRunLimit() {
        return maxDistanceRun;
    }

    public boolean doRun(int distnce) {
        if (distnce > maxDistanceRun) {
            System.out.println("done: Чел. (" + this.name + ") НЕ пробежал(а) " + distnce + ",(max= " + maxDistanceRun + ")");
            return false;
        }
        System.out.println("done: Чел. ("+this.name+") пробежал(а)      " + distnce);
        return true;
    }

    public boolean doJump(int Height) {
        if (Height > maxHeight) {
            System.out.println("done: Чел. (" + this.name + ") не перепрыгнул(а) " + Height + ",(max= " + maxHeight + ")");
            return false;
        }
        System.out.println("done: Чел ("+this.name+") перепрыгнул(а) " + Height);
        return true;
    }
}
