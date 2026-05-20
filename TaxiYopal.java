import java.util.Scanner;

public class TaxiYopal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Entrada de datos
        System.out.print("Tipo de vehículo (1=Motocarro, 2=Automóvil, 3=Camioneta): ");
        int V = sc.nextInt();
        if (V < 1 || V > 3) {
            System.out.println("Tipo de vehículo no válido");
            return;
        }

        System.out.print("Distancia del viaje en km: ");
        double km = sc.nextDouble();
        if (km <= 0) {
            System.out.println("Distancia inválida");
            return;
        }

        System.out.print("Hora de inicio del viaje (0-23): ");
        int hora = sc.nextInt();
        if (hora < 0 || hora > 23) {
            System.out.println("Hora inválida");
            return;
        }

        System.out.print("¿Es domingo o festivo? (S/N): ");
        String D = sc.next();
        if (!D.equals("S") && !D.equals("N")) {
            System.out.println("Respuesta S/N inválida");
            return;
        }

        System.out.print("¿Hay lluvia fuerte en la vía? (S/N): ");
        String L = sc.next();
        if (!L.equals("S") && !L.equals("N")) {
            System.out.println("Respuesta S/N inválida");
            return;
        }

        System.out.print("¿El viaje es rural (fuera del casco urbano)? (S/N): ");
        String R = sc.next();
        if (!R.equals("S") && !R.equals("N")) {
            System.out.println("Respuesta S/N inválida");
            return;
        }

        System.out.print("Tipo de pasajero (1=Frecuente, 2=Estudiante, 3=Adulto mayor, 4=Ocasional): ");
        int P = sc.nextInt();
        if (P < 1 || P > 4) {
            System.out.println("Tipo de pasajero no válido");
            return;
        }

        System.out.print("Edad del pasajero: ");
        int edad = sc.nextInt();
        if (edad < 0 || edad > 120) {
            System.out.println("Edad fuera de rango");
            return;
        }

        // ---------------- REGLA 1 ----------------
        double tarifaKm = 0;
        int tarifaMinima = 0;
        if (V == 1) {
            tarifaKm = 1200;
            tarifaMinima = 5000;
        } else if (V == 2) {
            tarifaKm = 2000;
            tarifaMinima = 8000;
        } else if (V == 3) {
            tarifaKm = 2800;
            tarifaMinima = 12000;
        }

        double subtotal = km * tarifaKm;
        boolean aplicoMinima = false; // variable booleana intermedia
        if (subtotal < tarifaMinima) {
            subtotal = tarifaMinima;
            aplicoMinima = true;
        }

        // ---------------- REGLA 2 ----------------
        double porcentajeRecargo = 0;
        if (hora >= 22 || hora < 5) { // uso de ||
            porcentajeRecargo += 20;
        }
        if (D.equals("S")) {
            porcentajeRecargo += 15;
        }
        if (L.equals("S")) {
            porcentajeRecargo += 10;
        }
        if (R.equals("S")) {
            porcentajeRecargo += 25;
        }

        double valorRecargo = subtotal * (porcentajeRecargo / 100);
        double totalConRecargos = subtotal + valorRecargo;

        // ---------------- REGLA 3 ----------------
        double porcentajeDescuento = 0;
        if (P == 1) {
            porcentajeDescuento = 10;
        } else if (P == 2) {
            porcentajeDescuento = 8;
        } else if (P == 3) {
            if (edad >= 60) {
                porcentajeDescuento = 12;
            } else {
                System.out.println("Inconsistencia: edad no corresponde a adulto mayor");
                P = 4; // reasignar a ocasional
                porcentajeDescuento = 0;
            }
        } else {
            porcentajeDescuento = 0;
        }

        double valorDescuento = totalConRecargos * (porcentajeDescuento / 100);
        double totalFinal = totalConRecargos - valorDescuento;

        // ---------------- REGLA 4 ----------------
        if (R.equals("N") && totalFinal < tarifaMinima) { // uso de &&
            totalFinal = tarifaMinima;
            System.out.println("Se aplicó tarifa solidaria mínima");
        }

        // ---------------- RECIBO ----------------
        System.out.println("\n--- RECIBO DE VIAJE ---");
        System.out.println("Vehículo: " + V + " | Kilómetros: " + km);
        System.out.println("Subtotal: $" + (int) subtotal);
        if (aplicoMinima) {
            System.out.println("Se aplicó tarifa mínima en la Regla 1");
        }
        System.out.println("Recargo total: " + porcentajeRecargo + "% → $" + (int) valorRecargo);
        System.out.println("Valor con recargos: $" + (int) totalConRecargos);
        System.out.println("Tipo de pasajero: " + P + " | Descuento: " + porcentajeDescuento + "% → $" + (int) valorDescuento);
        System.out.println("TOTAL A PAGAR: $" + (int) totalFinal);
    }
    
} 

