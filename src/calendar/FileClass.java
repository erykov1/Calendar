package calendar;
import java.io.*;
import java.util.Scanner;

class FileClass {
  String fileName;

  public FileClass(String name) {
    this.fileName = name;
  }

  void read() throws FileNotFoundException {
    File file = new File(fileName);
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine()) System.out.println(sc.nextLine());
  }

  void write(String text) throws IOException {
    File file = new File(fileName);
    if(!file.exists()) {
      file.createNewFile();
      System.out.println("File created");
    }
    FileWriter writer = new FileWriter(file,true);
    writer.write(text + "\n");
    writer.flush();
    writer.close();
  }

  void clearFile() throws IOException {
    PrintWriter writer = new PrintWriter(fileName);
    writer.print("");
    writer.flush();
    writer.close();
  }
}
