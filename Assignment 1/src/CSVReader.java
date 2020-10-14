import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
@author  Miguel Cruz (mcruz@mcruz.me)
 */

public class CSVReader {

    ArrayList<Long> upc14List;
    ArrayList<Long> upc12List;
    ArrayList<Integer> groupIdList;
    ArrayList<String> brandList;
    ArrayList<String> nameList;
    Hashtable<Long, String> productMap;


    public CSVReader() throws IOException {
        upc14List = new ArrayList<>();
        groupIdList = new ArrayList<>();
        upc12List = new ArrayList<>();
        brandList = new ArrayList<>();
        nameList = new ArrayList<>();
        productMap = new Hashtable<>();
        dumpCSV();

    }

    private void dumpCSV() throws IOException {
        String path = "Grocery_UPC_Database.csv";
        File f = new File(path);
        String line = "";
        System.out.println(f.getAbsolutePath());
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            if (!line.contains("upc14") || !line.contains("upc12")) {

                String pattern = "^(\".*?\"|.*?),(\".*?\"|.*?),(\".*?\"|.*?),(\".*?\"|.*?),(\".*?\"|.*?)$";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(line);
                m.find();

                int group_ID = Integer.parseInt(m.group(1));
                Long upc14 = Long.parseLong(m.group(2));
                Long upc12 = Long.parseLong(m.group(3));
                String brand = m.group(4);
                String name = m.group(5);
                groupIdList.add(group_ID);
                upc14List.add(upc14);
                upc12List.add(upc12);
                brandList.add(brand);
                nameList.add(name);
                productMap.put(upc14, name);

                //System.out.println("Group_ID: " + group_ID + " UPC14: " + upc14 + " UPC12: " + upc12 + " Brand: " + brand + " Name: " + name);
            }
        }
        br.close();
    }

    public ArrayList<Long> getUpc14List() {
        return upc14List;
    }

    public ArrayList<Long> getUpc12List() {
        return upc12List;
    }

    public ArrayList<String> getBrandList() {
        return brandList;
    }

    public ArrayList<String> getNameList() {
        return nameList;
    }

    public Hashtable<Long, String> getUpc14ProductMap() {
        return productMap;
    }
}