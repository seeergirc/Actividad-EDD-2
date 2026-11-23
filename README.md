# Historial de comandos Git
## 1. Creación y clonación del repositorio

Primero se creó un repositorio en GitHub. Posteriormente, se clonó en el ordenador para poder trabajar con él desde Visual Studio Code.

- **git clone https://github.com/seeergirc/Actividad-EDD-2**

    Después, se accedió a la carpeta del proyecto:

- **cd Actividad-EDD-2**

## 2. Desarrollo del backend

- **git switch backend**

    Cambia a la rama backend, donde se desarrolla exclusivamente el código Java.

- **git add Calculadora.java**

    Añade el archivo Calculadora.java a la zona de preparación de Git.

- **git commit -m "Crear servidor HTTP y operaciones matematicas"**

## 3. Desarrollo del frontend

- **git switch frontend**

    Cambia a la rama frontend, destinada exclusivamente al desarrollo de archivos HTML.

- **git add index.html**

    Añade el archivo index.html a la zona de preparación.

- **git commit -m "Crear interfaz HTML de la calculadora"**

    Guarda los cambios realizados en el frontend mediante un nuevo commit.

- **git push origin frontend**

    Publica la rama frontend en GitHub.

## 4. Fusión de las ramas en main

- **git switch main**

    Cambia a la rama principal del proyecto.

- **git merge frontend**

    Fusiona los cambios de la rama frontend en main, incorporando la interfaz HTML.

- **git merge backend**

    Fusiona los cambios de la rama backend en main, incorporando el código Java.

- **git push origin main**

    Publica en GitHub la rama principal con los cambios fusionados.

## 5. Comprobación final

- **git branch**

    Comprueba que existen las tres ramas del proyecto.

- **git status**

    Comprueba que no quedan cambios pendientes de guardar.