package fluentconditionals;

import java.util.function.Supplier;

public class FluentConditionalsReturn <T> {

    private final Supplier<T> supplier;
    private final boolean caseTest;

    public FluentConditionalsReturn(boolean caseTest, Supplier<T> supplier) {
        this.caseTest = caseTest;
        this.supplier = supplier;
    }

    public T orElse(Supplier<T> lowNum) {
        if (!caseTest) {
            return lowNum.get();
        } else
            return supplier.get();
    }

    public T orElse(T i) {
        if (!caseTest)
            return i;
        else
            return supplier.get();
    }

    public T orElseThrowE(RuntimeException e) {
        if (!caseTest)
            throw e;
        else return supplier.get();
    }

    public T orElseThrow(Supplier<RuntimeException> o) {
        if (!caseTest)
            throw o.get();
        else
            return supplier.get();
    }
}
