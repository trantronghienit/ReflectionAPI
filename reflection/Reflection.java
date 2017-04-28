/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Reflection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String className = "reflection.SinhVien";
            // vì mọi class trong java đều là class nên có 1 class tên Class đại diện cho tất cả các class ấy
            Class<?> clssSinhVien = Class.forName(className);     // tạo ra 1 object class đại diện cho class sinhvien
            SinhVien sv = (SinhVien) clssSinhVien.newInstance();   // tạo ra 1 đối tượng sinhvien dựa trên class đaij diện 
            Method[] methods = clssSinhVien.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("setTen")) {
                    method.invoke(sv, new String[]{"Hiến trần"}); // đối tượng đại diện , tham số cho hàm 
                }
            }
            System.out.println(sv.getTen());
            //====================================================

            System.out.println("==========lấy trên các Fields==============");
//            Field[] fields = clssSinhVien.getFields();    // chỉ lấy được những file public
            Field[] fields = clssSinhVien.getDeclaredFields();  // lấy tất cả các file có trong class 
            for (Field field : fields) {
                System.out.println("" + field.getName());
                // thử truy cập field private và gán giá trị 
                // tìm field nam có tên là tuổi  set quyền truy cập private và gán giá trị 
                if (field.getName().equals("Tuoi")) {
                    field.setAccessible(true);    // set quyền truy cập 
                    field.setInt(sv, 20);
                }
            }
            System.out.println("tuoi cua sinh vien: " + sv.getTuoi());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
