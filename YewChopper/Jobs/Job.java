package YewChopper.Jobs;

public abstract class Job {

    public Job() {
    }

    public int delay() {
        return 400;
    }

    public int priority() {
        return 0;
    }

    public abstract boolean activate();

    public abstract void execute();

}