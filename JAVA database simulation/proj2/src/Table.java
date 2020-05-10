import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Table {
    int numberOfAttributes;
    Attribute attribute[];
    String Header;
    public int recordNum = 0;
    public ArrayList<String> Record = new ArrayList<String>();


    public Table() {


    }


    public void setNumberOfAttributes(int numberOfAttributes) {
        this.numberOfAttributes = numberOfAttributes;
    }

    public ArrayList<String> getRecord() {
        return Record;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) throws IOException {
        Header = header;


    }

    public void Updateheader(String header) throws IOException {
        Header = header;
        UpdateHeaderfile(header);
    }

    public Attribute[] getAttribute() {
        return attribute;
    }

    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }

    public int getNumberOfAttributes() {
        return numberOfAttributes;
    }

    public void setAttribute(Attribute[] attribute) {
        this.attribute = attribute;
    }

    //helper function to modify file
    static void UpdateHeaderfile(String NewHeader) throws IOException {
        File file = new File("xyz.tb");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        ArrayList<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        lines.set(0, NewHeader);
        String newtext = "";
        FileWriter f2 = new FileWriter(file, false);
        PrintWriter printWriter = new PrintWriter(f2);

        for (int i = 0; i < lines.size(); i++) {
            printWriter.println(lines.get(i));  //New line
        }


        printWriter.close();

    }

    public void DeleteRecordinfile(int Index) throws IOException {
        File file = new File("xyz.tb");

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = "";
        ArrayList<String> lines = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();


        String newtext = "";

        if(recordNum-1>=0){
            recordNum--;
        }
        else{
            System.out.println("No record in that Index!");
            System.out.println("");

        }


        lines.remove(Index + 1);//remove Record
        Header = Header.substring(0, Header.length() - 2);
        Header += recordNum + "]";//update record number in header
        FileWriter f2 = new FileWriter(file, false);
        PrintWriter printWriter = new PrintWriter(f2);
        lines.set(0, Header);//update Header
        for (int i = 0; i < lines.size(); i++) {
            printWriter.println(lines.get(i));  //New line
        }

        UpdateRecordfromfile();

        printWriter.close();





    }


    public void UpdateRecordfromfile() throws IOException {
        try {
            File file = new File("xyz.tb");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            ArrayList<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();


            Record.clear();
            for (int i = 1; i < lines.size(); i++) {
                Record.add(lines.get(i));//New line

            }


        } catch (FileNotFoundException e) {
            System.out.println("file xyz.tb not found , An error occurred.");
            exit(0);
            //e.printStackTrace();
        }

    }
}
