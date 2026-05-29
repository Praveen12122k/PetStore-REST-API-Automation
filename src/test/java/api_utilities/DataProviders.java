package api_utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name="Data")
    public String[][] getdata() throws IOException {
        String path=".\\TestData\\APIDDT.xlsx";
        ExcelUtility xlutility= new ExcelUtility(path);
        int total_row=xlutility.getRowCount("Sheet1");
        int total_cell=xlutility.getCellCount("Sheet1",1);

        String[][] data = new String[total_row][total_cell];

        for (int i=1;i<=total_row;i++){
            for (int j=0;j<total_cell;j++){
                data[i-1][j]=xlutility.getCellData("Sheet1",i,j);
            }
        }
        return data;
    }

    @DataProvider(name="Username")
    public String[] getusername() throws IOException {
        String path=".\\TestData\\APIDDT.xlsx";
        ExcelUtility xlutility= new ExcelUtility(path);
        int total_row=xlutility.getRowCount("Sheet1");
        String[] data = new String[total_row];
        for (int i=1;i<=total_row;i++){
                data[i-1]=xlutility.getCellData("Sheet1",i,1);
        }
        return data;
    }
}
