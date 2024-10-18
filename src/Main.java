import java.util.ArrayList;
import java.util.List;

class Vehicle {
    private String brand;
    private String model;
    private int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public void startEngine() {
        System.out.println("Двигатель " + brand + " " + model + " запущен.");
    }

    public void stopEngine() {
        System.out.println("Двигатель " + brand + " " + model + " остановлен.");
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
}

class Car extends Vehicle {
    private int doors;
    private String transmission;

    public Car(String brand, String model, int year, int doors, String transmission) {
        super(brand, model, year);
        this.doors = doors;
        this.transmission = transmission;
    }

    @Override
    public void startEngine() {
        System.out.println("Автомобиль " + getBrand() + " " + getModel() + " с " + doors + " дверями и " + transmission + " трансмиссией запущен.");
    }

    @Override
    public void stopEngine() {
        System.out.println("Автомобиль " + getBrand() + " " + getModel() + " остановлен.");
    }
}

class Motorcycle extends Vehicle {
    private String bodyType;
    private boolean hasBox;

    public Motorcycle(String brand, String model, int year, String bodyType, boolean hasBox) {
        super(brand, model, year);
        this.bodyType = bodyType;
        this.hasBox = hasBox;
    }

    @Override
    public void startEngine() {
        System.out.println("Мотоцикл " + getBrand() + " " + getModel() + " (" + bodyType + ") запущен.");
    }

    @Override
    public void stopEngine() {
        System.out.println("Мотоцикл " + getBrand() + " " + getModel() + " остановлен.");
    }
}

class Garage {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println(vehicle.getClass().getSimpleName() + " " + vehicle.getBrand() + " " + vehicle.getModel() + " добавлен в гараж.");
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicles.remove(vehicle)) {
            System.out.println(vehicle.getClass().getSimpleName() + " " + vehicle.getBrand() + " " + vehicle.getModel() + " удален из гаража.");
        } else {
            System.out.println("Транспортное средство не найдено в гараже.");
        }
    }

    public void showVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("Гараж пуст.");
        } else {
            System.out.println("В гараже находятся:");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.getBrand() + " " + vehicle.getModel() + ", " + vehicle.getYear());
            }
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}

class Fleet {
    private List<Garage> garages = new ArrayList<>();

    public void addGarage(Garage garage) {
        garages.add(garage);
        System.out.println("Гараж добавлен в автопарк.");
    }

    public void removeGarage(Garage garage) {
        if (garages.remove(garage)) {
            System.out.println("Гараж удален из автопарка.");
        } else {
            System.out.println("Гараж не найден в автопарке.");
        }
    }

    public Vehicle findVehicle(String brand, String model) {
        for (Garage garage : garages) {
            for (Vehicle vehicle : garage.getVehicles()) {
                if (vehicle.getBrand().equals(brand) && vehicle.getModel().equals(model)) {
                    return vehicle;
                }
            }
        }
        return null;
    }

    public void showGarages() {
        if (garages.isEmpty()) {
            System.out.println("В автопарке нет гаражей.");
        } else {
            System.out.println("В автопарке находятся гаражи:");
            for (Garage garage : garages) {
                garage.showVehicles();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("Лада", "Веста", 2020, 4, "Автоматическая");
        Car car2 = new Car("ГАЗ", "Волга", 2021, 4, "Механическая");
        Motorcycle motorcycle1 = new Motorcycle("ИЖ", "Юпитер", 2019, "Туристический", true);

        Garage garage1 = new Garage();
        Garage garage2 = new Garage();

        garage1.addVehicle(car1);
        garage1.addVehicle(motorcycle1);
        garage2.addVehicle(car2);

        Fleet fleet = new Fleet();

        fleet.addGarage(garage1);
        fleet.addGarage(garage2);

        fleet.showGarages();

        Vehicle foundVehicle = fleet.findVehicle("Лада", "Веста");
        if (foundVehicle != null) {
            System.out.println("Найдено транспортное средство: " + foundVehicle.getBrand() + " " + foundVehicle.getModel());
        } else {
            System.out.println("Транспортное средство не найдено.");
        }

        garage1.removeVehicle(car1);
        fleet.showGarages();
    }
}
