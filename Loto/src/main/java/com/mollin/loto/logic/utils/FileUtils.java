package com.mollin.loto.logic.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

/**
 * Classe utilitaire pour manipuler les chemins et les fichiers.
 *
 * @author MOLLIN Florian
 */
public class FileUtils {

    /**
     * Test si le path est un repertoire.
     *
     * @param path Le path à tester.
     * @return Vrai si le path est un dossier.
     */
    public static boolean isDirectory(Path path) {
        return Files.isDirectory(path);
    }

    /**
     * Test si le path est un fichier.
     *
     * @param path Le path à tester.
     * @return Vrai si le path est un fichier.
     */
    public static boolean isFile(Path path) {
        return Files.isRegularFile(path);
    }

    /**
     * Test si le path existe.
     *
     * @param path Le path à tester.
     * @return Vrai si le path existe.
     */
    public static boolean exists(Path path) {
        return Files.exists(path);
    }

    /**
     * Test si le path est un fichier accessible en écriture.
     *
     * @param path Le path à tester.
     * @return Vrai si le path est un fichier accessible en écriture.
     */
    public static boolean isWritable(Path path) {
        return Files.isWritable(path);
    }

    /**
     * Test si le path est un fichier accessible en lecture.
     *
     * @param path Le path à tester.
     * @return Vrai si le path est un fichier accessible en lecture.
     */
    public static boolean isReadable(Path path) {
        return Files.isReadable(path);
    }

    /**
     * Créé le dossier donné. <br/>
     * Créé aussi les sous dossiers si il n'existent pas.
     *
     * @param directories Le repertoire à créer.
     * @return Vrai si la création s'est correctement déroulée.
     */
    public static boolean createDirectories(Path directories) {
        try {
            if (!exists(directories)) {
                Files.createDirectories(directories);
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * Créé le fichier donné. <br/>
     * Créé aussi les sous dossiers si il n'existent pas.
     *
     * @param file Le fichier à créer.
     * @return Vrai si la création s'est correctement déroulée.
     */
    public static boolean createFile(Path file) {
        createDirectories(file.getParent());
        try {
            Files.createFile(file);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * Ecrit une chaine de caractère dans un fichier. <br/>
     * Créé aussi les dossiers contenant le fichier si ils n'existent pas.
     *
     * @param file le fichier dans lequel écrire.
     * @param str  la chaine à écrire.
     * @return Vrai si l'écriture a fonctionnée.
     */
    public static boolean write(Path file, String str) {
        createDirectories(file.getParent());
        try {
            Files.write(file, Arrays.asList(str));
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * Lecture d'un fichier.
     *
     * @param file le fichier à lire.
     * @return La liste des lignes du fichier lu.
     */
    public static List<String> read(Path file) {
        if (isReadable(file)) {
            try {
                return Files.readAllLines(file);
            } catch (IOException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Suppression d'un repertoire (meme non vide)
     *
     * @param directory Le repertoire à supprimer
     * @return Vrai si pas d'erreur
     */
    public static boolean deleteDirectory(Path directory) {
        if (Files.isDirectory(directory)) {
            try {
                Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
                return true;
            } catch (IOException ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Copie d'un fichier dans répertoire (ecrase le fichier si il existe deja)
     *
     * @param fileToCopy Le fichier à copier
     * @param dirOfCopy  Le repertoire de destination
     * @return Vrai si pas d'erreur
     */
    public static boolean copyFile(Path fileToCopy, Path dirOfCopy) {
        if (!isFile(fileToCopy)) {
            return false;
        }
        if (isFile(dirOfCopy)) {
            return false;
        }
        createDirectories(dirOfCopy);
        try {
            Files.copy(fileToCopy, dirOfCopy, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * Convertit une chaine de caractère en un nom de fichier valide (supprime
     * les caractères spéciaux interdits)
     *
     * @param name La chaine à convertir
     * @return Une chaine valide pour une nom de fichier
     */
    public static String toValidFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

}
