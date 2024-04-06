/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Tus
 */
public class KtInput {

    static Scanner sc = new Scanner(System.in);

    private KtInput() {
    }

    public static int getChoice() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(KtInput.getInput());
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Input không chính xác1!!!");
            }
        }
    }

    public static Date getInputDate() {
        Date date = null;
        String strDate = "";
        SimpleDateFormat dateInput = new SimpleDateFormat("dd/MM/yyyy");// 32/10/2022 -> 01/11/2022  11/11/2022 -> 11/11/2022
        while (date == null) {
            System.out.print("Date (dd/MM/yyyy) > ");
            strDate = KtInput.getInput();
            try {
                date = dateInput.parse(strDate);
                if (!strDate.equals(dateInput.format(date))) {
                    System.out.println("Input không chính xác!!!");
                    date = null;
                }
            } catch (ParseException e) {
                System.out.println("Input không chính xác!!!");
            }
        }
        return date;
    }

    public static double getInputDiem() {
        double choice = -1;
        while (choice == -1) {
            try {
                choice = Double.parseDouble(KtInput.getInput());
                if(choice < 0 || choice > 10){
                    System.out.print("Diem khong hop le! Nhap lai: ");
                    choice = -1;
                }
            } catch (NumberFormatException e) {
                System.out.print("Input không chính xác!!! Nhap lai: ");
            }
        }
        return choice;
    }

    public static int getInputNumber() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(KtInput.getInput());
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Input không chính xác!!!");
            }
        }
    }

    public static String getInput() {
        return sc.nextLine();
    }
}
