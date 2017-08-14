
import fluentconditionals.FluentConditionals;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

@Test
public class IfElseTest {

    @Test
    public void shouldNotRunWithoutOrElse(){

        Client client = mock(Client.class);

        FluentConditionals.when(true).then(client::taskTrue);

        verify(client, times(0)).taskFalse();
        verify(client, times(0)).taskTrue();

    }
    @Test
        public void shouldUseCorrectFunctionBaseOnBoolean(){

            Client client = mock(Client.class);

            FluentConditionals.when(true).then(client::taskTrue).orElse(client::taskFalse);

            verify(client, times(0)).taskFalse();
            verify(client, times(1)).taskTrue();

        }

    @Test
    public void shouldUseCorrectFunctionBaseSupplier(){

        Client client = mock(Client.class);
        Supplier<Boolean> supplier = () -> true;

        FluentConditionals.when(supplier).then(client::taskTrue).orElse(client::taskFalse);

        verify(client, times(0)).taskFalse();
        verify(client, times(1)).taskTrue();

    }

    @Test
    public void shouldUseCorrectFunctionBaseSupplierFalse(){

        Client client = mock(Client.class);
        Supplier<Boolean> supplier = () -> false;

        FluentConditionals.when(supplier).then(client::taskTrue).orElse(client::taskFalse);

        verify(client, times(1)).taskFalse();
        verify(client, times(0)).taskTrue();

    }

    @Test (expectedExceptions = RuntimeException.class)
    public void shouldThrowExceptionIfFalse() {


        FluentConditionals.when(false).then(()->{}).orElseThrow(RuntimeException::new);
        FluentConditionals.when(false).then(()->{}).orElseThrowE(new RuntimeException());
    }

    @Test
    public void shouldNoThrowExceptionIfTrue() {

        FluentConditionals.when(true).then(()->{}).orElseThrow(RuntimeException::new);
        FluentConditionals.when(true).then(()->{}).orElseThrowE(new RuntimeException());
    }


    @Test
    public void shouldReturnAppropriateValueWhenTrue(){

        assertEquals(FluentConditionals.when(true).thenReturn(()->100).orElse(()->0), Integer.valueOf(100));
    }

    @Test
    public void shouldReturnAppropriateValueWhenFalse(){

        assertEquals(FluentConditionals.when(false).thenReturn(()->100).orElse(()->0), Integer.valueOf(0));
        assertEquals(FluentConditionals.when(false).thenReturn(()->100).orElse(0), Integer.valueOf(0));

    }

    @Test
    public void shouldReturnTrueValue() {

        assertEquals(FluentConditionals.when(true).thenReturn(()->100).orElseThrow(RuntimeException::new), Integer.valueOf(100));
        assertEquals(FluentConditionals.when(true).thenReturn(()->100).orElseThrowE(new RuntimeException()), Integer.valueOf(100));
        assertEquals(FluentConditionals.when(true).thenReturn(100).orElseThrowE(new RuntimeException()), Integer.valueOf(100));
        assertEquals(FluentConditionals.when(true).thenReturn(100).orElseThrow(RuntimeException::new), Integer.valueOf(100));

    }

    @Test (expectedExceptions = RuntimeException.class)
    public void shouldThrowExceptionDespiteValueIfFalse() {

        FluentConditionals.when(false).thenReturn(()->100).orElseThrowE(new RuntimeException());
        FluentConditionals.when(false).thenReturn(()->100).orElseThrow(RuntimeException::new);
        FluentConditionals.when(false).thenReturn(100).orElseThrow(RuntimeException::new);
        FluentConditionals.when(false).thenReturn(100).orElseThrowE(new RuntimeException());

    }

    @Test
        public void shouldReturnFirstLetterOfString() {
        String given = "fee";

        FluentConditionals.given(given).when(true).orElse();

        }
}