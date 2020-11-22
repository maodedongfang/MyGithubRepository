package utils;

import java.io.*;

public class Methods {

    /***
     * @Description //在指定文件夹(copyFolder)中检索是否包含含有指定字符串(specialWords)的文件，将包含的文件复制到目标文件夹(goalFolder)的根目录下。
     * @version 1.0
     * @Param [copyFolder, goalFolder]
     * @return void
     **/
    public static void searchFolder1(File searchFolder, File goalFolder, String searchWords) throws IOException {


        File[] allFile = searchFolder.listFiles();    //获取要复制的文件夹下的所有文件(夹)
        //遍历获取到的所有文件(夹)
        for(File aFile : allFile){

            //根据是文件还是文件夹进行不同的处理
            if(aFile.isDirectory()){
                //如果是文件夹则将该文件夹作为新的被检索文件夹查询
                searchFolder1(aFile, goalFolder,searchWords);
            }else if(aFile.isFile()){
                //如果是文件
                String name = aFile.getName();
                //判断该文件文件名是否包含关键字，如果包含则复制该文件：创建一个文件指向要复制的目标文件夹下，该文件名称与复制的原文件相同，调用copyFile方法完成复制。
                if(name.contains(searchWords)){

                    File goalFile = new File(goalFolder, name);
                    copyFile(aFile, goalFile);
                }

            }
        }

    }


    /***
     * @Description //在指定文件夹(copyFolder)中检索是否包含含有指定字符串(specialWords)的文件夹，对于 找到的每个文件夹，在目标文件夹下创建一个与该文件夹同名的文件夹，并将前者文件夹内的全部文件复制到后者中。
     * @version 1.0
     * @Param [copyFolder, goalFolder]
     * @return void
     **/
    public static void searchFolder2(File searchFolder, File goalFolder, String searchWords) throws IOException {


        File[] allFile = searchFolder.listFiles();
        for (File aFile: allFile){

            if(aFile.isDirectory()){
                String name = aFile.getName();
                if(name.contains(searchWords)){
                    String Name = aFile.getName();
                    File newFolder = new File(goalFolder,Name);
                    newFolder.mkdir();
                    copyFolder(aFile,newFolder);
                    /*File[] files = aFile.listFiles();
                    for (File file: files){
                        File newFolderFile = new File(newFolder,file.getName());
                        copyFile(file,newFolderFile);
                    }*/
                }else {
                    searchFolder2(aFile,goalFolder,searchWords);
                }

            }

        }

    }



    private static void copyFolder(File fromFolder, File toFolder) throws IOException {
        File[] allFile = fromFolder.listFiles();    //获取要复制的文件夹下的所有文件(夹)

        //遍历获取到的所有文件(夹)
        for(File aFile : allFile){

            //根据是文件还是文件夹进行不同的处理
            if(aFile.isDirectory()){
                //如果是文件夹则将该文件夹作为新的被检索文件夹查询
                copyFolder(aFile, toFolder);
            }else if(aFile.isFile()){
                //如果是文件
                String name = aFile.getName();
                File goalFile = new File(toFolder, name);
                copyFile(aFile, goalFile);

            }
        }
    }


    /***
     * @Description //将aFile文件中的内容复制到goalFile文件中
     * @version 1.0
     * @Param [aFile, goalFile]
     * @return void
     **/
    private static void copyFile(File aFile, File goalFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(aFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(goalFile));
        int len = 0;
        byte[] bys = new byte[1024];
        while((len = bis.read(bys)) != -1){
            bos.write(bys, 0, len);
        }
        bis.close();
        bos.close();
    }

    public static boolean deleteFiles(File goalFolder){
        boolean exists = goalFolder.exists();
        if (exists){
            try {
                deleteFile(goalFolder);
                deleteFolder(goalFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return exists;
    }

    private static void deleteFile(File goalFolder) throws IOException {
        File[] allFile = goalFolder.listFiles();
        for (File aFile : allFile) {
            if (aFile.isDirectory()) {
                deleteFolder(aFile);
            } else if (aFile.isFile()) {
                aFile.delete();
            }

        }

    }

    private static void deleteFolder(File goalFolder) throws IOException {
        File[] allFile = goalFolder.listFiles();
        if (allFile != null) {
            for (File aFile : allFile) {
                if (aFile.isDirectory()) {
                    deleteFolder(aFile);
                } else if (aFile.isFile()) {
                    aFile.delete();
                }

            }
        }
        goalFolder.delete();

    }


}
