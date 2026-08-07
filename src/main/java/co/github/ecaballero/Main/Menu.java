package co.github.ecaballero.Main;

public final class Menu {
  private Menu() {
  }

  public static void MainOptions() {
    System.out.println("\n===== OPTIONS MENU =====");
    System.out.println("1. Students");
    System.out.println("2. Courses");
    System.out.println("3. Enrollments");
    System.out.println("0. Exit");
  }

  public static void StudentOptions() {
    System.out.println("\n===== STUDENT MENU =====");
    System.out.println("1. Create Student");
    System.out.println("2. Find By Id Student");
    System.out.println("3. List All Students");
    System.out.println("4. Update Student");
    System.out.println("5. Delete Student");
    System.out.println("0. Exit");
  }

  public static void CourseOptions() {
    System.out.println("\n===== COURSE MENU =====");
    System.out.println("1. Create Course");
    System.out.println("2. Find By Id Course");
    System.out.println("3. List All Courses");
    System.out.println("4. Update Course");
    System.out.println("5. Delete Course");
    System.out.println("0. Exit");
  }

  public static void EnrollmentOptions() {
    System.out.println("\n===== ENROLLMENT MENU =====");
    System.out.println("1. Create Enrollment");
    System.out.println("2. Find By Id Enrollment");
    System.out.println("3. List All Enrollments");
    System.out.println("4. Cancel Enrollment");
    System.out.println("5. Delete Enrollment");
    System.out.println("0. Back");
  }
}
