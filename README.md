# UCRED Movies Browser

[![CI](https://github.com/rivasjm/ucred-java-ui-movies/actions/workflows/ci.yml/badge.svg)](https://github.com/rivasjm/ucred-java-ui-movies/actions/workflows/ci.yml)
[![Release](https://github.com/rivasjm/ucred-java-ui-movies/actions/workflows/release.yml/badge.svg)](https://github.com/rivasjm/ucred-java-ui-movies/actions/workflows/release.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Aplicación de escritorio Java con interfaz Swing para explorar películas populares usando la API de [The Movie Database (TMDb)](https://www.themoviedb.org/).

## 📸 Características

- 🎬 **Explorar películas populares** - Visualiza las películas más populares del momento
- 🖼️ **Pósters de alta calidad** - Carga asíncrona de imágenes desde TMDb
- 📝 **Información detallada** - Ver detalles completos al hacer clic en una película
- 💰 **Datos financieros** - Presupuesto, recaudación y calificaciones
- 🎯 **Interfaz intuitiva** - Grid de películas con panel de detalles lateral

## 🚀 Instalación

### Instaladores nativos (recomendado)

Descarga el instalador para tu sistema operativo desde la [página de releases](https://github.com/rivasjm/ucred-java-ui-movies/releases):

- **Windows**: `UCRED Movies-1.0.0.exe` - Instalador MSI con acceso directo en el menú de inicio
- **macOS**: `UCRED Movies-1.0.0.dmg` - Imagen de disco, arrastra a Aplicaciones
- **Linux**: `ucred-movies_1.0.0_amd64.deb` - Paquete Debian

### JAR ejecutable

Si prefieres usar el JAR directamente:

1. Asegúrate de tener Java 17 o superior instalado
2. Descarga `ucred-java-ui-movies-1.0.0.jar` desde releases
3. Ejecuta:

```bash
export TMDB_API_KEY=tu_api_key_aqui
java -jar ucred-java-ui-movies-1.0.0.jar
```

## 🔑 Configuración de API Key

Necesitas una API key de TMDb (es gratis). Ver [API_KEY_SETUP.md](API_KEY_SETUP.md) para instrucciones detalladas.

**Resumen rápido:**

1. Regístrate en https://www.themoviedb.org/
2. Ve a Settings → API y solicita una clave
3. Configura la variable de entorno:

```bash
# Linux/macOS
export TMDB_API_KEY=tu_api_key_aqui

# Windows PowerShell
$env:TMDB_API_KEY="tu_api_key_aqui"

# Windows CMD
set TMDB_API_KEY=tu_api_key_aqui
```

## 🛠️ Desarrollo

### Requisitos

- Java 17 o superior
- Maven 3.6+
- Cuenta de TMDb con API key

### Compilar desde código fuente

```bash
# Clonar repositorio
git clone https://github.com/rivasjm/ucred-java-ui-movies.git
cd ucred-java-ui-movies

# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Crear JAR ejecutable
mvn package

# Ejecutar aplicación
export TMDB_API_KEY=tu_api_key
java -jar target/ucred-java-ui-movies-1.0.0.jar
```

### Crear instaladores nativos localmente

Requiere JDK 17+ con jpackage:

```bash
# Compilar JAR primero
mvn package -DskipTests

# Windows
jpackage --input target --name "UCRED Movies" --main-jar ucred-java-ui-movies-1.0.0.jar --type exe --app-version 1.0.0 --vendor "UCRED" --win-shortcut

# macOS
jpackage --input target --name "UCRED Movies" --main-jar ucred-java-ui-movies-1.0.0.jar --type dmg --app-version 1.0.0 --vendor "UCRED"

# Linux
jpackage --input target --name ucred-movies --main-jar ucred-java-ui-movies-1.0.0.jar --type deb --app-version 1.0.0 --vendor "UCRED" --linux-shortcut
```

## 🧪 Testing

El proyecto incluye tests unitarios con cobertura del servicio TMDb:

```bash
# Ejecutar todos los tests
mvn test

# Tests con cobertura (opcional, requiere jacoco plugin)
mvn verify
```

**Nota:** Los tests de UI con AssertJ Swing están excluidos en la build automática porque requieren un entorno con display. Para ejecutarlos manualmente, quita la exclusión en `pom.xml` y ejecuta en un entorno con GUI.

## 🏗️ Arquitectura

```
src/main/java/es/unican/ucred/movies/
├── MovieBrowserApp.java          # Punto de entrada
├── model/
│   ├── Movie.java                # Modelo básico de película
│   └── MovieDetails.java         # Modelo extendido con detalles
├── service/
│   ├── ITMDbService.java         # Interfaz del servicio
│   └── TMDbService.java          # Implementación con OkHttp + Gson
├── ui/
│   ├── MainFrame.java            # Ventana principal
│   ├── MovieGridPanel.java       # Grid de pósters
│   └── MovieDetailsPanel.java   # Panel de detalles
└── util/
    └── ImageLoader.java          # Carga asíncrona de imágenes
```

## 📦 Tecnologías

- **Java 17** - Lenguaje base
- **Maven** - Gestión de dependencias y build
- **Swing** - Framework GUI nativo
- **OkHttp 4.12** - Cliente HTTP para llamadas a API
- **Gson 2.10** - Parsing de JSON
- **JUnit 5** - Framework de testing
- **Mockito 5** - Mocking para tests
- **MockWebServer** - Simulación de API en tests
- **AssertJ Swing** - Testing de UI (opcional)
- **jpackage** - Creación de instaladores nativos

## 🔄 CI/CD

El proyecto usa GitHub Actions para:

- **CI** (`.github/workflows/ci.yml`): Build y tests en cada push/PR a main
- **Release** (`.github/workflows/release.yml`): Creación automática de instaladores nativos para Windows, macOS y Linux cuando se publica un tag `v*`

Ver [RELEASES.md](RELEASES.md) para instrucciones sobre cómo crear releases.

## 📝 API de TMDb

Esta aplicación usa la API v3 de The Movie Database:

- **Endpoint base**: `https://api.themoviedb.org/3`
- **Popular movies**: `/movie/popular`
- **Movie details**: `/movie/{id}`
- **Imágenes**: `https://image.tmdb.org/t/p/{size}{path}`

Documentación completa: https://developer.themoviedb.org/docs

## 🎓 Propósito educativo

Este proyecto forma parte de una serie de ejemplos educativos sobre GitHub Actions y CI/CD:

1. [ucred-dotnet-ui](https://github.com/rivasjm/ucred-dotnet-ui) - Calculadora Avalonia con releases multi-plataforma
2. [ucred-dotnet-api](https://github.com/rivasjm/ucred-dotnet-api) - API REST ASP.NET Core con Railway
3. [ucred-web](https://github.com/rivasjm/ucred-web) - Calculadora web con WebDAV deployment
4. **ucred-java-ui-movies** - Aplicación Java Swing con jpackage (este proyecto)

Cada proyecto demuestra diferentes tecnologías, patrones de testing y estrategias de deployment.

## 📄 Licencia

MIT License - Ver [LICENSE](LICENSE) para más detalles.

## 👤 Autor

**Juan María Rivas**  
Universidad de Cantabria

---

**Nota**: Esta aplicación requiere conexión a internet para obtener datos de películas desde TMDb. Los pósters y datos se obtienen en tiempo real desde la API.
