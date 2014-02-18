package robindecroon.careconnect.util;

/**
 * Created by robindecroon on 14/02/14.
 */
public class ICPCParser {

//    /**
//     * Read csv file.
//     *
//     * @return the map
//     * @throws IOException Signals that an I/O exception has occurred.
//     */
//    public static Map<String,List<String>> readICPCCSVFile(Context context) throws IOException {
//        InputStream input = context.getAssets().open("icpc.csv");
//        BufferedReader in = new BufferedReader(new InputStreamReader(input));
//
//        String[] chapters = context.getResources().getStringArray(R.array.icpc_chapters);
//
//        Map<String, List<String>> lists = new HashMap<String, List<String>>();
//        for(String chapter: chapters) {
//            lists.put(chapter, new ArrayList<String>());
//        }
//
//        try {
//            in.readLine();
//            String line;
//            while ((line = in.readLine()) != null) {
//                try {
//                    String[] values = line.split(";");
//                    String chapter = chapters[Integer.parseInt(values[0])];
//                    String code = values[1];
//                    String description = values[2];
//                    lists.get(chapter).add(code + " "  + description);
//                } catch (Exception e) {
//                    Log.e("ICPCParser", "Error in country CSV file!");
//                }
//            }
//        } catch (Exception e) {
//            Log.e("ICPCParser", "Error in country CSV file!");
//        }
//        return lists;
//    }
}
