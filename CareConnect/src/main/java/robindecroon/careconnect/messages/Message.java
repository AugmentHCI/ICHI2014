package robindecroon.careconnect.messages;

/**
 * Created by robindecroon on 13/01/14.
 */
public class Message {

    private String title;

    private String content;

    private int iconType;

    private MessageType type;

    private static long ID_COUNTER;

    private final long id = ID_COUNTER++;

    public Message(String title, String content, int iconType, MessageType type) {
        this.title = title;
        this.content = content;
        this.iconType = iconType;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIconType() {
        return iconType;
    }

    public void setIconType(int iconType) {
        this.iconType = iconType;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

}
