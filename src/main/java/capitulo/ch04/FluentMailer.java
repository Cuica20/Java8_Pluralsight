package capitulo.ch04;

import java.util.function.Consumer;

public class FluentMailer {
    private FluentMailer() {
    }

    public FluentMailer from( final String address ) {
        /* ... */
        return this;
    }

    public FluentMailer to( final String address ) {
        /* ... */
        return this;
    }

    public FluentMailer subject( final String line ) {
        /* ... */
        return this;
    }

    public FluentMailer body( final String message ) {
        /* ... */
        return this;
    }

    public static void send( final Consumer<FluentMailer> block ) {
        final FluentMailer mailer = new FluentMailer();
        block.accept( mailer );
        System.out.println( "sending..." );
    }

    public static void main( final String[] args ) {
        // BobK: Where the fuck did mailer come from (or bob as the case may
        // be)? How is it that bob is an instanceof FluentMailer?
        FluentMailer.send( bob -> bob.from( "build@agiledeveloper.com" )
                .to( "venkats@agiledeveloper.com" )
                .subject( "build notification" )
                .body( "...much better..." ) );
    }

}
