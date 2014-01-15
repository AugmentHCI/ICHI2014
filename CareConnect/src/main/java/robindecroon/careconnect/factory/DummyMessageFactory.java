package robindecroon.careconnect.factory;

import java.util.ArrayList;
import java.util.List;

import robindecroon.careconnect.R;
import robindecroon.careconnect.messages.Message;
import robindecroon.careconnect.messages.MessageType;

/**
 * Created by robindecroon on 13/01/14.
 */
public class DummyMessageFactory {

    private static List<Message> dummyMessages;

    private static List<Message> labMessages;
    private static List<Message> referralMessages;

    private static List<Message> generateMixedMessages() {
        ArrayList<Message> list = new ArrayList<Message>();
        int countLab = 0;
        int countRef = 0;
        for (int i = 0; i < 20; i++) {
            list.add(new Message("Lab " + countLab++,"Inhoud", R.drawable.lab_result_icon, MessageType.LAB));
            list.add(new Message("Verwijsbrief " + countRef++, "Patient kan best blablabla zien", R.drawable.referral_icon, MessageType.REFERRAL));
        }
        return list;
    }

//    private static List<Message> generateLabMessages() {
//        ArrayList<Message> list = new ArrayList<Message>();
//        for (int i = 0; i < 20; i++) {
//            list.add(new Message("Lab result UZ Leuven", "waarde 1 fqkdjfh kdjfh qkdjfh qdkj hqkldjhf qkldjhklsqdjhf kdj qkjdh dkkqjd  dqkjhd qklqsdjhd  jdkfh kqkjd h lqdhfjqjd hldjfhqeluih qei uqmgi jre tqrgqli ghuqirlg rukghv:f kiugrhs lc:kjvhwdfgklu qirghlwdfkjghw", R.drawable.lab_result_icon, MessageType.LAB));
//        }
//        return list;
//    }
//
//    private static List<Message> generateReferralMessages() {
//        ArrayList<Message> list = new ArrayList<Message>();
//        for (int i = 0; i < 20; i++) {
//            list.add(new Message("Verwijsbrief", "Patient kan best blablabla zien", R.drawable.referral_icon, MessageType.REFERRAL));
//        }
//        return list;
//    }

    public static List<Message> getDummyMixedMessages() {
        if (dummyMessages == null)
            dummyMessages = generateMixedMessages();
        return dummyMessages;
    }

//    public static List<Message> getDummyLabMessages() {
//        if (labMessages == null)
//            labMessages = generateLabMessages();
//        return labMessages;
//    }
//
//    public static List<Message> getDummyReferralMessages() {
//        if (referralMessages == null)
//            referralMessages = generateReferralMessages();
//        return referralMessages;
//    }

}
