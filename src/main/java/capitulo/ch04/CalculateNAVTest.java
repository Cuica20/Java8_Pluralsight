package capitulo.ch04;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CalculateNAVTest {

    @Test
    public void computeStockWorth() {
        // Note how easy it is, with lambdas, to "stub" out the priceFinder
        final CalculateNAV calculateNAV = new CalculateNAV( ticker -> new BigDecimal( "6.01" ) );
        BigDecimal expected = new BigDecimal( "6010.00" );
        // I was unaware of the delta thing.
        Assert.assertEquals( 0, calculateNAV.computeStockWorth( "GOOG", 1000 )
                .compareTo( expected ), 0.001 );
    }

    @Test
    public void yahooFinanceIntegrationTest() {
        final CalculateNAV calculateNav = new CalculateNAV( YahooFinance::getPrice );
        BigDecimal oneGOOGShare = calculateNav.computeStockWorth( "GOOG", 1 );
        Assert.assertNotNull( oneGOOGShare );
        BigDecimal zero = new BigDecimal( 0 );
        Assert.assertTrue( oneGOOGShare.compareTo( zero ) >= 0 );

        // What if it changes between oneGOOGShare calc and now?
        BigDecimal expectedTenGOOGShares = oneGOOGShare.multiply( new BigDecimal( 10 ) );
        BigDecimal actualTenGOOGShares = calculateNav.computeStockWorth( "GOOG", 10 );
        Assert.assertEquals( expectedTenGOOGShares, actualTenGOOGShares );
    }
}
