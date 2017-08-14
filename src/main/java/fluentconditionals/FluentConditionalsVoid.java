package fluentconditionals;

import java.util.function.Supplier;

public class FluentConditionalsVoid {
    private final Runnable runnableMethod;
    private final boolean caseTest;

    public FluentConditionalsVoid(boolean caseTest, Runnable method) {
        this.caseTest = caseTest;
        this.runnableMethod = method;
    }

    public void orElse(Runnable runnableTask) {
        if (!caseTest) {
            runnableTask.run();
        } else
            runnableMethod.run();
    }

    public void orElseThrowE(RuntimeException e) {
        if (!caseTest) throw e;
        else runnableMethod.run();
    }


    public void orElseThrow(Supplier<RuntimeException> ex) throws RuntimeException {
        if (!caseTest)
            throw ex.get();
        else
            runnableMethod.run();
    }
}
