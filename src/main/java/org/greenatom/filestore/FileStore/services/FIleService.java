package org.greenatom.filestore.FileStore.services;


import org.greenatom.filestore.FileStore.models.File;
import org.greenatom.filestore.FileStore.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FIleService {
    private final FileRepository fileRepository;

    @Autowired
    public FIleService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public int save(File file){
        enrichFile(file);

        fileRepository.save(file);
        return file.getId();
    }

    public File findOne(int id){
        return fileRepository.findById(id).orElse(null);
    }

    private void enrichFile(File file){
        file.setCreationDate(LocalDateTime.now());
    }
}
