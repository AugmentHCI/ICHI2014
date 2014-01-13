package robindecroon.careconnect.factory;

import java.util.ArrayList;
import java.util.List;

import robindecroon.careconnect.R;
import robindecroon.careconnect.messages.Message;

/**
 * Created by robindecroon on 13/01/14.
 */
public class DummyMessageFactory {

    private static List<Message> dummyMessages;


    private static List<Message> generateMessages() {

        ArrayList<Message> list =  new ArrayList<Message>();
        for (int i = 0; i < 20; i++) {
            list.add(new Message("Lab result UZ Leuven","waarde 1 fqkdjfh kdjfh qkdjfh qdkj hqkldjhf qkldjhklsqdjhf kdj qkjdh dkkqjd  dqkjhd qklqsdjhd  jdkfh kqkjd h lqdhfjqjd hldjfhqeluih qei uqmgi jre tqrgqli ghuqirlg rukghv:f kiugrhs lc:kjvhwdfgklu qirghlwdfkjghw", R.drawable.lab_result_icon));
            list.add(new Message("Verwijsbrief", "Patient kan best blablabla zien",R.drawable.referral_icon));
        }
        return list;
    }

    public static List<Message> getDummyMessages() {
        if(dummyMessages == null)
            dummyMessages = generateMessages();
        return dummyMessages;
    }

}
