package instigatemobile.com.amot;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    public static List<User> users = new ArrayList<>();

    public static void fillInitialData () {
        users.add(new User("Arman", "AAAAAAAAAAAAAAA AAAAAAAAAAAAAA AAAAAAAAAAA",
                "https://cdn.pixabay.com/photo/2017/04/05/11/56/image-in-the-image-2204798_960_720.jpg",
                "0777777777", "arman@gmail.com"));
        users.add(new User("Karen", "KKKKKKKKK KKKKKKKKKKKKKKKKKkkkkk KKKKKKKKKKKKKKKKKKK",
                "https://3.bp.blogspot.com/-9Iu3-Xnvtig/Wntv1F6CKII/AAAAAAAAAR0/CvCLy-jFSUIfM9vjj6UdjZigK7LKBnSPgCLcBGAs/s1600/2.jpg",
                "0777777770", "karen@gmail.com"));
        users.add(new User("Anush", "AAAAANNNNNNAAAAAAAAAA AAAAAnNNNNNNNAAAAAAAAA AAAAAAAAAAA",
                "https://thumb9.shutterstock.com/display_pic_with_logo/63814/582804565/stock-photo-image-of-young-businesswoman-pulling-a-boulder-with-success-word-while-climbing-on-the-stair-582804565.jpg",
                "0777777700", "anush@gmail.com"));
    }
}
