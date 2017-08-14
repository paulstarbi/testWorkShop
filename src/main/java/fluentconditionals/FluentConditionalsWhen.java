package fluentconditionals;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FluentConditionalsWhen {

    public static Consumer<String> doNothingConsumer;
    private final String givenValue;
    private boolean testCase;
    private Consumer<String> stringConsumer;


    public FluentConditionalsWhen(String givenValue) {
        this.givenValue = givenValue;
    }

    public FluentConditionalsWhen when(boolean testCase) {
        this.testCase = testCase;
        return this;
    }

    public FluentConditionalsWhen when(Supplier<Boolean> testCase) {
        return when(testCase.get());
    }

    public FluentConditionalsWhen then(Consumer<String> stringConsumer) {
        this.stringConsumer = stringConsumer;
        return this;
    }

    public void orElse(Consumer<String> consumer){
        if (!testCase)
            consumer.accept(givenValue);
        else
            stringConsumer.accept(givenValue);
    }


    public void orElseThrowE(RuntimeException e) {
        if (!testCase) throw e;
        else stringConsumer.accept(givenValue);
    }


    public void orElseThrow(Supplier<RuntimeException> ex) throws RuntimeException {
        if (!testCase)
            throw ex.get();
        else
            stringConsumer.accept(givenValue);
    }


}
