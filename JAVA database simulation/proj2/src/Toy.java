import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Toy {

    static Table xyz;
    int NumberofRec = 0;

    public static void main(String args[]) throws IOException {


        while (true) {
            Scanner sc = new Scanner(System.in);
            if (xyz == null) {
                xyz = new Table();
                initialTable();
            }

            System.out.println("Choose A Command from the list:");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("1-Toy Create");
            System.out.println("2-Toy Header");
            System.out.println("3-Toy Insert");
            System.out.println("4-Toy Display rid");
            System.out.println("5-Toy Delete rid");
            System.out.println("6-Toy Search");
            System.out.println("7-Exit");
            System.out.println(" ");
            System.out.println("Enter a Command Number:");
            try {
                int CommandNum = sc.nextInt();

                if (CommandNum == 1) {
                    toyCreate();
                } else if (CommandNum == 2) {
                    toyHeader();
                } else if (CommandNum == 3) {
                    toyInsert();
                } else if (CommandNum == 4) {
                    toyDisplayRid();
                } else if (CommandNum == 5) {
                    toyDeleteRid();
                } else if (CommandNum == 6) {
                    toySearch();
                } else if (CommandNum == 7) {
                    System.out.println("");
                    System.out.println("Bye");
                    System.out.println("");
                    exit(0);
                } else {
                    System.out.println("Wrong Command Number choose one of the list please");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input Should be a Command Number ");
                System.out.println("");
            }
        }
    }
    private static void toySearch() throws IOException {
        initialTable();
        xyz.UpdateRecordfromfile();

        ArrayList<String> record = xyz.getRecord();
        System.out.println("Choose a Search Condition Attribute by the index: ");
        Attribute[] attributes = xyz.getAttribute();
        for (int i = 0; i < attributes.length; i++) {
            System.out.println(i + " " + attributes[i].name);
        }
        Scanner sc = new Scanner(System.in);
        int index = Integer.parseInt(sc.nextLine());

        System.out.println(attributes[index].name + "Condition is ? : ");
        String condtion = sc.nextLine();

        //System.out.println(recordweneed);
        for (int i = 0; i < record.size(); i++) {
            String[] Values = record.get(i).split("\\|");
            Values[0] = Values[0].substring(1);//remove {
            Values[Values.length - 1] = Values[Values.length - 1].substring(0, Values[Values.length - 1].length() - 1);//remove }7

            for (int j = 0; j < Values.length; j++) {
                if (j == index && condtion.equals(Values[j])) {
                    System.out.println(record.get(i));
                }
            }

        }
    }

    private static void toyDeleteRid() throws IOException {
        File file = new File("xyz.tb");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("file xyz.tb not found , An error occurred.");
            exit(0);
            //e.printStackTrace();
        }
        System.out.println("Enter Index of Record to Remove: ");
        Scanner sc = new Scanner(System.in);
        int index = Integer.parseInt(sc.nextLine());
       xyz.DeleteRecordinfile(index);
    }

    private static void toyDisplayRid() throws IOException {
        System.out.print("Enter Record Index: ");
        Scanner sc = new Scanner(System.in);
        int index = Integer.parseInt(sc.nextLine());

        initialTable();
        xyz.UpdateRecordfromfile();
        ArrayList<String> record = xyz.getRecord();
        String recordweneed = "";
        if(index>=0&&index<record.size()){
            recordweneed = record.get(index);
        }
        else{
            System.out.println("no Record what that INDEX!");
            System.out.println("");
            return;
        }

        //System.out.println(recordweneed);

        String[] Values = recordweneed.split("\\|");
        Values[0] = Values[0].substring(1);//remove {
        Values[Values.length - 1] = Values[Values.length - 1].substring(0, Values[Values.length - 1].length() - 1);//remove }
        Attribute[] attr = xyz.getAttribute();
        for (int i = 0; i < Values.length; i++) {
            System.out.println(attr[i].getName() + ": " + Values[i]);
        }
    }


    private static void toyInsert() throws IOException {
        try {
            File myObj = new File("xyz.tb");
            Scanner myReader = new Scanner(myObj);

            String header = myReader.nextLine();//get first line in file
            String[] theheader = header.split("\\{"); //sperate from records
            myReader.close();
            xyz.setHeader(theheader[0]); //if not set already
            readHeader(theheader[0]);
            Attribute[] attr = xyz.getAttribute();
            Scanner sc = new Scanner(System.in);
            String Recordtoinsert = "{";
            for (int i = 0; i < xyz.attribute.length; i++) {
                System.out.println(attr[i].name + ":  ");
                String input = sc.nextLine();
                if (!checkinput(input)) {
                    System.out.println("Reserved charecter Used: error");
                    exit(0);
                }
                String type = "";
                if (attr[i].type == 1) {
                    try {
                        Integer.parseInt(input);
                        //it worked
                    } catch (NumberFormatException e) {
                        System.out.println("fail wrong type");
                        exit(0);
                    }
                    type = "integer";
                } else if (attr[i].type == 2) {
                    try {
                        Double.parseDouble(input);
                        //it worked
                    } catch (NumberFormatException e) {
                        System.out.println("fail wrong type");
                        exit(0);
                    }
                    type = "double";
                } else if (attr[i].type == 3) {
                    if (!(input.equals("F") || input.equals("f") || input.equals("t") || input.equals("T"))) {
                        System.out.println("fail wrong type");
                        exit(0);
                    }
                    type = "Boolean";
                }

                if (i == xyz.attribute.length - 1) {
                    Recordtoinsert += input + "}";
                } else if (i < xyz.attribute.length) {
                    Recordtoinsert += input + "|";
                }

            }

            FileWriter f2 = new FileWriter(myObj, true);
            PrintWriter printWriter = new PrintWriter(f2);

            if (xyz.recordNum == 0) {
                printWriter.println();
            }
            printWriter.println(Recordtoinsert);  //New line
            printWriter.close();

            System.out.println(Recordtoinsert);
            xyz.Record.add(Recordtoinsert);

            xyz.recordNum++;
            header = xyz.getHeader();
            header = header.substring(0, header.length() - 2);
            header += xyz.recordNum + "]";//update record number in header

            xyz.Updateheader(header);

        } catch (FileNotFoundException e) {
            System.out.println("file xyz.tb not found , An error occurred.");
            exit(0);
            //e.printStackTrace();
        }
    }

    private static boolean checkinput(String input) {
        if (input.indexOf("]") != -1 || input.indexOf("[") != -1 || input.indexOf("{") != -1 || input.indexOf("}") != -1 || input.indexOf("|") != -1) {
            return false;
        }
        return true;
    }

    private static void toyHeader() throws FileNotFoundException {
        try {
            File myObj = new File("xyz.tb");
            Scanner myReader = new Scanner(myObj);

            String header = myReader.nextLine();//get first line in file
            String[] theheader = header.split("\\{"); //sperate from records

            myReader.close();

            readHeader(theheader[0]);//chnage to update evyething
            System.out.println(header);
            String[] names = header.split("]");

            String numbofatr[] = names[0].split("\\[");
            System.out.println("Number of attributes" + numbofatr[1]);
            for (int i = 1; i < 1 + Integer.parseInt(numbofatr[1]); i++) {
                //System.out.println(names[i]);
                String[] value = names[i].split("\\[");
                String[] finalsplit = value[1].split(":");
                System.out.println("Attribute name " + finalsplit[0]);
                String type = " ";
                if (finalsplit[1].equals("1")) {
                    type = "integer";
                } else if (finalsplit[1].equals("2")) {
                    type = "double";
                } else if (finalsplit[1].equals("3")) {
                    type = "Boolean";
                } else if (finalsplit[1].equals("4")) {
                    type = "String";
                }
                System.out.println("Attribute Type " + type);
            }
            String numofrecords[] = names[1 + Integer.parseInt(numbofatr[1])].split("\\[");

            System.out.println("Number of Records :" + numofrecords[1]);
        } catch (FileNotFoundException e) {
            System.out.println("file xyz.tb not found , An error occurred.");
            exit(0);
            //e.printStackTrace();
        }

    }

    private static void toyCreate() throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Attribute> attr = new ArrayList<Attribute>();
        System.out.println("1-Toy Create Chosen");
        while (true) {
            Attribute atr1;
            System.out.print("Attribute name:  ");
            String attrName = sc.nextLine();
            if (!checkinput(attrName)) {
                System.out.println("Reserved charecter Used: error");
                exit(0);
            }
            System.out.println("Select a type: 1. integer; 2. double; 3. boolean; 4. string");
            int attrnum = Integer.parseInt(sc.nextLine());
            atr1 = new Attribute(attrName, attrnum);
            attr.add(atr1);

            System.out.println("More attribute? (y/n)");
            String moreOrno = sc.nextLine();

            if (!moreOrno.equals("y") && !moreOrno.equals("n")) {
                System.out.println("Invalid input");
                exit(0);
            }

            if (moreOrno.equals("n")) {//no more attributes
                int numofatrr = attr.size();

                xyz.setNumberOfAttributes(numofatrr);//set the number of attributes
                Attribute[] attributes = new Attribute[numofatrr];
                String header = "[" + numofatrr + "]"; //trying to make the header
                for (int i = 0; i < attr.size(); i++) {
                    attributes[i] = attr.get(i); //copy everything from the temp arraylist to actual attributes
                    header += "[" + attributes[i].getName() + ":" + attributes[i].getType() + "]";

                }
                header += "[" + 0 + "]";
                xyz.setHeader(header);
                xyz.setAttribute(attributes); //set the attribuites in the table object


                try {
                    File myObj = new File("xyz.tb");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }

                    FileWriter f2 = new FileWriter(myObj, false);
                    f2.write(header);
                    f2.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

        }
    }

    static Attribute[] readHeader(String header) {
        String[] names = header.split("]");
        String numbofatr[] = names[0].split("\\[");
        Attribute[] attr = new Attribute[Integer.parseInt(numbofatr[1])];

        int j = 0;
        for (int i = 1; i < names.length - 1; i++) {

            //System.out.println(names[i]);
            String[] value = names[i].split("\\[");
            String[] finalsplit = value[1].split(":");
            Attribute AsingleAttr = new Attribute(finalsplit[0], Integer.parseInt(finalsplit[1]));
            attr[j] = AsingleAttr;
            j++;
        }
        String numofrecords[] = names[names.length - 1].split("\\[");
        //System.out.println(numofrecords[1]);

        xyz.setRecordNum(Integer.parseInt(numofrecords[1]));
        xyz.setAttribute(attr);
        return attr;
    }

    static void initialTable() {
        try {
            File myObj = new File("xyz.tb");
            Scanner myReader = new Scanner(myObj);

            String header = myReader.nextLine();//get first line in file
            String[] theheader = header.split("\\{"); //sperate from records
            myReader.close();
            xyz.setHeader(theheader[0]); //if not set already
            readHeader(theheader[0]);
        } catch (FileNotFoundException e) {
            // e.printStackTrace();

        } catch (IOException e) {
            //  e.printStackTrace();
        }


    }


}
