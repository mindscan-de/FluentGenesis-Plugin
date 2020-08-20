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

        String code = "if (add) \n" + //
                        "   this.playerList.add(player); \n" + //
                        "else \n" + //
                        "    this.playerList.remove(player); \n" + //
                        "return this.containsPlayer(player);";

        // act
        service.requestMethodNamePredictionsPOST( code, 6 );

        // assert

    }

}
