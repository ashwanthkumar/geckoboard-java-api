package org.paules.geckoboard.api.widget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.paules.geckoboard.api.json.GraphType;

public class RAGColumnsAndNumbersTest {

    @Test
    public void testJson() throws JsonProcessingException, IOException {
        RAGColumnsAndNumbers ragNumbersOnly = new RAGColumnsAndNumbers( "1234", GraphType.STANDARD );
        ragNumbersOnly.setRed( "Test-red", 123 );
        ragNumbersOnly.setAmber( "Test-amber", 1234 );
        ragNumbersOnly.setGreen( "Test-green", 12345, "$" );

        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree( ragNumbersOnly.toJson() );

        assertEquals( "standard", node.get( "type" ).asText() );

        assertEquals( 3, node.get( "item" ).size() );
        assertEquals( "Test-red", node.get( "item" ).get( 0 ).get( "text" ).asText() );
        assertEquals( 123, node.get( "item" ).get( 0 ).get( "value" ).asInt() );
        assertNull( node.get( "item" ).get( 0 ).get( "prefix" ) );

        assertEquals( "Test-amber", node.get( "item" ).get( 1 ).get( "text" ).asText() );
        assertEquals( 1234, node.get( "item" ).get( 1 ).get( "value" ).asInt() );
        assertNull( node.get( "item" ).get( 1 ).get( "prefix" ) );

        assertEquals( "Test-green", node.get( "item" ).get( 2 ).get( "text" ).asText() );
        assertEquals( 12345, node.get( "item" ).get( 2 ).get( "value" ).asInt() );
        assertEquals( "$", node.get( "item" ).get( 2 ).get( "prefix" ).asText() );
    }

}
