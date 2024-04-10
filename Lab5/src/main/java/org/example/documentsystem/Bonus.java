package org.example.documentsystem;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Bonus {
    private Map<String, Set<String>> employeeAbilities;

    public Bonus(String excelFilePath) throws IOException, InvalidFormatException {
        this.employeeAbilities = readExcel(excelFilePath);
    }

    private Map<String, Set<String>> readExcel(String excelFilePath) throws IOException, InvalidFormatException {
        Map<String, Set<String>> abilitiesMap = new HashMap<>();
        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int whichRow=0;
        for (Row row : sheet) {
            whichRow++;
            if(whichRow==1)
                continue;
            String employeeName = row.getCell(0).getStringCellValue();
            Set<String> abilities = new HashSet<>();
            for (Cell cell : row) {
                if (cell.getColumnIndex() > 0) {
                    abilities.add(cell.getStringCellValue());
                }
            }
            abilitiesMap.put(employeeName, abilities);
        }

        fis.close();
        return abilitiesMap;
    }


    public List<Set<String>> findMaximalGroups() {
        List<Set<String>> maximalGroups = new ArrayList<>();
        Set<String> processedEmployees = new HashSet<>();

        for (String employee : employeeAbilities.keySet()) {
            if (!processedEmployees.contains(employee)) {
                Set<String> group = new HashSet<>();
                findGroupWithCommonAbilities(employee, group, processedEmployees);
                maximalGroups.add(group);
            }
        }

        return maximalGroups;
    }

    private void findGroupWithCommonAbilities(String employee, Set<String> group, Set<String> processedEmployees) {
        group.add(employee);
        processedEmployees.add(employee);

        Set<String> abilities = employeeAbilities.get(employee);
        for (String e : employeeAbilities.keySet()) {
            if (!processedEmployees.contains(e) && hasCommonAbility(employeeAbilities.get(e), abilities)) {
                findGroupWithCommonAbilities(e, group, processedEmployees);
            }
        }
    }

    private boolean hasCommonAbility(Set<String> abilities1, Set<String> abilities2) {
        for (String ability : abilities1) {
            if (abilities2.contains(ability)) {
                return true;
            }
        }
        return false;
    }

}
