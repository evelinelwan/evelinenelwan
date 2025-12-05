import java.util.*;

class Mahasiswa {
    private String nama;
    private String nim;
    private double nilai;

    public void setData(String nama, String nim, double nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
    }

    public void cetakStatus() {
        System.out.println("Nama  : " + nama);
        System.out.println("NIM   : " + nim);
        System.out.println("Nilai : " + nilai);
        System.out.println("Status: " + (nilai > 70 ? "LULUS" : "TIDAK LULUS"));
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah mahasiswa: ");
        System.out.flush();
        int jumlah = sc.nextInt();
        sc.nextLine(); // clear buffer

        ArrayList<Mahasiswa> daftar = new ArrayList<>();

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\n=== Data Mahasiswa ke-" + (i + 1) + " ===");

            System.out.print("Masukkan Nama  : ");
            System.out.flush();
            String nama = sc.nextLine();

            System.out.print("Masukkan NIM   : ");
            System.out.flush();
            String nim = sc.nextLine();

            System.out.print("Masukkan Nilai : ");
            System.out.flush();
            double nilai = sc.nextDouble();
            sc.nextLine(); // clear buffer

            Mahasiswa mhs = new Mahasiswa();
            mhs.setData(nama, nim, nilai);
            daftar.add(mhs);
        }

        System.out.println("\n===== HASIL KELULUSAN =====");

        for (int i = 0; i < daftar.size(); i++) {
            System.out.println("\nMahasiswa ke-" + (i + 1));
            daftar.get(i).cetakStatus();
        }
    }
}
