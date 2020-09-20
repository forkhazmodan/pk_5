package com.kp.chukhnovm.hw5;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class FileFilter implements java.io.FileFilter {

    String[] exts;

    FileFilter(String... exts) {
        this.exts = exts;
    }

    private boolean isExtAllowed(String ext) {
        for (String item: this.exts) {
            if(item.equals(ext)) return true;
        }

        return false;
    }

    @Override
    public boolean accept(File pathname) {
        String ext = FilenameUtils.getExtension(pathname.getAbsolutePath());
        return isExtAllowed(ext);
    }
}
