package fluentconditionals;

import java.util.function.Supplier;

public class FluentConditionals {
    boolean caseTest;

    public static final Runnable doNothing = () -> {};

    FluentConditionals(boolean testCase) {
        this.caseTest = testCase;
    }

    public static FluentConditionalsWhen given(Supplier<String> supplier){
        return new FluentConditionalsWhen(supplier.get());
    }
    public static FluentConditionalsWhen given(String givenValue){
        return new FluentConditionalsWhen(givenValue);
    }

    public static FluentConditionals when(boolean testCase) {
        return new FluentConditionals(testCase);
    }

    public static FluentConditionals when(Supplier<Boolean> testCase) {
        return when(testCase.get());
    }

    public FluentConditionalsVoid then(Runnable runnableTask) {
        return new FluentConditionalsVoid(caseTest, runnableTask);

    }

    public <T> FluentConditionalsReturn <T> thenReturn(Supplier<T> supplierTask) {
           return new FluentConditionalsReturn<>(caseTest, supplierTask);
    }

    public <T> FluentConditionalsReturn <T> thenReturn (T value) {
          return thenReturn(() -> value);
    }
}
