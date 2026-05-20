[Uploading README.md…]()
# Tarificador TaxiYopal S.A.S. — Parcial Condicionales

## Datos del estudiante

| Campo | Información |
|---|---|
| **Nombre completo** | _(Ana Sofia Ortiz Pinzon )_ |
| **Programa** | Ingeniería de Sistemas |
| **Semestre** | _(primer semestre)_ |
| **Curso** | Algoritmos I — Uniremington Yopal |
| **Lenguaje** | Java 21 (OpenJDK 21) |

---

## Descripción

Programa que calcula automáticamente la tarifa de un viaje para la empresa **TaxiYopal S.A.S.**, aplicando cuatro reglas de negocio en orden:

1. **Regla 1** — Tarifa base por kilómetro según tipo de vehículo, con ajuste a tarifa mínima.
2. **Regla 2** — Recargos acumulativos: nocturno, domingo/festivo, lluvia fuerte, viaje rural.
3. **Regla 3** — Descuento único según tipo de pasajero, con validación de consistencia para adulto mayor.
4. **Regla 4** — Tarifa solidaria mínima para viajes urbanos.

Desarrollado **únicamente con estructuras condicionales** (`if`, `if-else`, `else if`, anidadas, `&&`, `||`, `!`). Sin ciclos, arreglos, listas ni `switch`.

---

## Instrucciones para compilar y ejecutar

### Requisito previo
Tener instalado **Java JDK 11 o superior**.  
Verificar con:
```bash
java -version
```

### Compilar
```bash
cd src
javac TaxiYopal.java
```

### Ejecutar
```bash
java TaxiYopal
```

El programa solicita los datos uno por uno de forma interactiva en la consola.

---

## Casos de prueba (Sección 7)

### Caso 1 — Sin recargos ni descuentos
**Entrada:** V=2, km=10, hora=14, D=N, L=N, R=N, P=4, edad=35

```
--- RECIBO DE VIAJE ---
Vehículo: 2 | Kilómetros: 10.0
Subtotal: $20000
Recargo total: 0.0% → $0
Valor con recargos: $20000
Tipo de pasajero: 4 | Descuento: 0.0% → $0
TOTAL A PAGAR: $20000
```
✅ **Total esperado: $20.000**

---

### Caso 2 — Ajuste a tarifa mínima (Regla 1)
**Entrada:** V=1, km=2, hora=10, D=N, L=N, R=N, P=4, edad=30

```
--- RECIBO DE VIAJE ---
Vehículo: 1 | Kilómetros: 2.0
Subtotal: $5000
Se aplicó tarifa mínima en la Regla 1
Recargo total: 0.0% → $0
Valor con recargos: $5000
Tipo de pasajero: 4 | Descuento: 0.0% → $0
TOTAL A PAGAR: $5000
```
✅ **Total esperado: $5.000** (tarifa mínima Motocarro aplicada sobre $2.400 calculados)

---

### Caso 3 — Recargos nocturno + dominical + rural; descuento pasajero frecuente
**Entrada:** V=3, km=15, hora=23, D=S, L=N, R=S, P=1, edad=45

```
--- RECIBO DE VIAJE ---
Vehículo: 3 | Kilómetros: 15.0
Subtotal: $42000
Recargo total: 60.0% → $25200
Valor con recargos: $67200
Tipo de pasajero: 1 | Descuento: 10.0% → $6720
TOTAL A PAGAR: $60480
```
✅ **Total esperado: $60.480**  
Recargos: nocturno 20% + dominical 15% + rural 25% = 60% → $42.000 × 1,60 = $67.200  
Descuento frecuente: 10% → $67.200 × 0,90 = $60.480

---

### Caso 4 — Recargo nocturno + lluvia; descuento adulto mayor
**Entrada:** V=2, km=8, hora=4, D=N, L=S, R=N, P=3, edad=68

```
--- RECIBO DE VIAJE ---
Vehículo: 2 | Kilómetros: 8.0
Subtotal: $16000
Recargo total: 30.0% → $4800
Valor con recargos: $20800
Tipo de pasajero: 3 | Descuento: 12.0% → $2496
TOTAL A PAGAR: $18304
```
✅ **Total esperado: $18.304**  
Recargos: nocturno 20% + lluvia 10% = 30% → $16.000 × 1,30 = $20.800  
Descuento adulto mayor: 12% → $20.800 × 0,88 = $18.304

---

### Caso 5 — Tipo de vehículo inválido
**Entrada:** V=4

```
Tipo de vehículo no válido
```
✅ El programa muestra el error y termina sin realizar ningún cálculo.

---

### Caso 6 — Kilómetros inválidos
**Entrada:** V=2, km=-5

```
Distancia inválida
```
✅ El programa muestra el error y termina sin realizar ningún cálculo.

---

## Requisitos técnicos cumplidos

| # | Requisito | Dónde en el código |
|---|---|---|
| 9 | `if-else` simple | Tarifa mínima Regla 1: `if (subtotal < tarifaMinima)` |
| 10 | Cadena `if-else if-else` ≥ 3 ramas | Selección de vehículo (V==1, V==2, V==3) y tipo de pasajero (P==1..4) |
| 11 | Condicional anidada | Regla 3: `if (P == 3) { if (edad >= 60) {...} else {...} }` |
| 12 | Operador `\|\|` | Hora nocturna: `hora >= 22 \|\| hora < 5` |
| 12 | Operador `&&` | Regla 4: `R.equals("N") && totalFinal < tarifaMinima` |
| 13 | Variable booleana intermedia | `boolean aplicoMinima = false;` |
| 14 | Sin ciclos, arreglos ni switch | Cumplido — el flujo completo usa solo condicionales |
| 15 | Indentado, nombres descriptivos, ≥ 5 comentarios | Bloques `// REGLA 1` a `// REGLA 4` y `// RECIBO` |

---

## Reflexión final

Al resolver este caso aprendí que las estructuras condicionales permiten modelar reglas de negocio reales con precisión, siempre que el orden de aplicación sea el correcto. El mayor reto fue entender que los recargos deben acumularse primero y solo después aplicar el descuento, ya que invertir ese orden produce un resultado incorrecto aunque el código compile sin errores. También fue importante manejar la validación de la inconsistencia del adulto mayor usando una condicional anidada, porque dependía de dos variables al mismo tiempo (el tipo de pasajero y la edad). El operador `||` resultó clave para expresar en una sola línea la condición nocturna, que abarca dos rangos de hora distintos. Aprendí además que una variable booleana intermedia como `aplicoMinima` no solo cumple un requisito técnico, sino que hace el código más legible y evita repetir la misma comparación en el bloque de salida. En general, este ejercicio me mostró que diseñar la lógica en papel antes de escribir código ahorra tiempo y reduce errores, especialmente cuando las reglas son acumulativas y dependen unas de otras.

---

## Estructura del repositorio

```
parcial-condicionales-APELLIDO/
├── README.md
├── .gitignore
└── src/
    └── TaxiYopal.java
```

---

## Commits realizados

```
feat: agrega estructura base del proyecto y .gitignore para Java
feat: agrega validación de entradas - Regla 0 (Sección 5)
feat: implementa Regla 1 tarifa base y Regla 2 recargos acumulativos
feat: implementa Regla 3 descuentos, Regla 4 tarifa solidaria y recibo final
```
