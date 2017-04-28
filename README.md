# Reflection API

## Reflection API là gì ?
+ là một kĩ thuật cho phép có thể duyệt và thay đổi các thuộc tính và phuơng thức của một class hoặc một interface tại thời điểm runtime

## Tại sao Reflection API được ra đời ?
+ Đã bao giờ bạn tự thắc mắc làm sao các framework MVC có thể mapping url request vào chính xác từng controller, hay làm sao spring lại có thể khởi tạo bean từ xml configuration or annotation, injection các giá trị vào bean? Tất cả là nhờ đến kỹ thuật reflection

## Sử dụng thế nào ?

```
// reflection.SinhVien      ==> package  reflection class SinhVien
String className = "reflection.SinhVien";
//================= Create Object ======================
// vì mọi class trong java đều là class nên có 1 class tên Class đại diện cho tất cả các class ấy
   Class<?> clssSinhVien = Class.forName(className);     // tạo ra 1 object class đại diện cho class sinhvien
// tạo đối tượng khoong dùng từ khóa new 
   SinhVien sv = (SinhVien) clssSinhVien.newInstance();   // tạo ra 1 đối tượng sinhvien dựa trên class đại diện 
   
//================= get Method ======================
Method[] methods = clssSinhVien.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("setTen")) {
                    method.invoke(sv, new String[]{"Hiến trần"}); // đối tượng đại diện , tham số cho hàm 
                }
            }
System.out.println(sv.getTen());    

//================= get Field ======================

//            Field[] fields = clssSinhVien.getFields();    // chỉ lấy được những file public
            Field[] fields = clssSinhVien.getDeclaredFields();  // lấy tất cả các file có trong class 
            for (Field field : fields) {
                System.out.println("" + field.getName());
                
                // thử truy cập field private và gán giá trị 
                // tìm field nam có tên là tuổi  set quyền truy cập private và gán giá trị 
                
                if (field.getName().equals("Tuoi")) {
                    field.setAccessible(true);    // set quyền truy cập 
                    field.setInt(sv, 20); // <==> sv.Tuoi nhưng field Tuoi modifi private
                }
            }
            System.out.println("tuoi cua sinh vien: " + sv.getTuoi());
```

