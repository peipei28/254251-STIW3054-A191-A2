package com.engpeipei;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class display {
    JSONObject[] obj;
    display(JSONObject[] obj){
        this.obj=obj;
    }

    public void display() throws JSONException {
        System.out.println("| No  |  Login ID  |  Number of repositories | Number of follower | Number of Following |     ID     |");
        System.out.println("------|------------|-------------------------|--------------------|---------------------|------------|");
        for(int x = 0; x < obj.length; x++) {
            System.out.printf("| %d  |  %s  |  %d | %d | %d  | %d |\n", x+1, obj[x].getString("login"), obj[x].getInt("public_repos"), obj[x].getInt("followers"), obj[x].getInt("following"), obj[x].getInt("id"));
        }
        toExcel();

    }

    public void toExcel() {

        String excelFile = "Github Follower Details.xls";
        System.out.println("\nwriting the" +excelFile+"...");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Github Follower Details");

        try{
            for(int i =0; i<obj.length;i++) {
                HSSFRow row = sheet.createRow(i);
                HSSFCell cell1 = row.createCell(0);
                cell1.setCellValue(obj[i].getString("login"));
                HSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(obj[i].getInt("public_repos"));
                HSSFCell cell3 = row.createCell(2);
                cell3.setCellValue(obj[i].getInt("followers"));
                HSSFCell cell4 = row.createCell(3);
                cell4.setCellValue(obj[i].getInt("following"));
                HSSFCell cell5 = row.createCell(4);
                cell5.setCellValue(obj[i].getInt("id"));
            }
            FileOutputStream outputFile = new FileOutputStream(excelFile);
            workbook.write(outputFile);
            outputFile.flush();
            outputFile.close();
            System.out.println(excelFile+ "is create successfully");
        } catch (FileNotFoundException e){
            System.out.println("Error : Failed to write the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
