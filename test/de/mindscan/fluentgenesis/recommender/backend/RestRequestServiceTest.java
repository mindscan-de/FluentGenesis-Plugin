package de.mindscan.fluentgenesis.recommender.backend;

import org.junit.jupiter.api.Test;;

public class RestRequestServiceTest {

    @Test
    public void testRequestMethodNamePredictionsGET() throws Exception {
        // arrange
        RestRequestService service = new RestRequestService();

        // act
        service.requestMethodNamePredictionsGET();

        // assert
    }

    @Test
    public void testRequestMethodNamePredictionsPOST() throws Exception {
        // arrange
        RestRequestService service = new RestRequestService();

        // act
        service.requestMethodNamePredictionsPOST( "", 6 );

        // assert

    }

}
