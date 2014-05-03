package nl.pvanassen.geckoboard.api.widget;

import java.util.LinkedList;
import java.util.List;

import nl.pvanassen.geckoboard.api.Push;
import nl.pvanassen.geckoboard.api.error.ValidationException;
import nl.pvanassen.geckoboard.api.json.list.ListItem;

import com.google.gson.annotations.SerializedName;

/**
 * List widget type http://www.geckoboard.com/developers/custom-widgets/widget-types/list
 * 
 * @author Paul van Assen
 */
public class ListWidget extends Push {
    @SerializedName( "item" )
    private final List<ListItem> items = new LinkedList<ListItem>();

    public ListWidget( String widgetKey ) {
        super( widgetKey );
    }

    /**
     * Create an item that can be filled by the app
     * 
     * @return A newly created item
     */
    public ListItem createItem(String text) {
        ListItem item = new ListItem(text);
        items.add( item );
        return item;
    }

    @Override
    protected void validate() throws ValidationException {
        if ( items.size() == 0 ) {
            throw new ValidationException( "Items", "Must be filled" );
        }
    }

}
