import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ArrayList;

public class ParseHtmlTable {


    public static String getHtmlTab(String theCompanyName) throws IOException{

        String HtmlInput = "";


        String[] items = theCompanyName.trim().split(" ");
        for (int j=0;j<items.length;j++){
            if (j<items.length-1){
                HtmlInput+=items[j]+"+";
            }
            HtmlInput+=items[j];
        }


        String theUrl = "https://www.marketwatch.com/tools/quotes/lookup.asp?siteID=mktw&Lookup="+HtmlInput+"&Country=us&Type=All";




        org.jsoup.nodes.Document doc = Jsoup.connect(theUrl).get();

        org.jsoup.select.Elements rows = doc.select("tr");


        String result = "<table>";

        int i=0;
        int j=0;


        for(org.jsoup.nodes.Element row :rows)
        {
            result+="<tr>";
            org.jsoup.select.Elements columns = row.select("td");
            for (org.jsoup.nodes.Element column:columns)
            {
                result+="<td>"+column.text()+"</td>";
//                System.out.print(column.text()) ;
            }
            result+="</tr>";
//            System.out.println();
        }
        result+="</table>";
        return result;

    }

    public static void main(String[] args) throws IOException {
        System.out.println(ParseHtmlTable.getHtmlTab("Microsoft"));
    }
}