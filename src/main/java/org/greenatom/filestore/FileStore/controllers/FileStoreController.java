package org.greenatom.filestore.FileStore.controllers;

import jakarta.validation.Valid;
import org.greenatom.filestore.FileStore.dto.FileDTO;
import org.greenatom.filestore.FileStore.models.File;
import org.greenatom.filestore.FileStore.services.FIleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/file_store")
public class FileStoreController {
    private final FIleService fileService;
    private ModelMapper modelMapper;

    @Autowired
    public FileStoreController(final FIleService fileService, ModelMapper modelMapper) {
        this.fileService = fileService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public int createFile(@RequestBody @Valid FileDTO fileDTO,
                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {

        }

        return fileService.save(convertToFile(fileDTO));
    }

    @GetMapping("/{id}")

    private File convertToFile(FileDTO fileDTO) {
        File file = modelMapper.map(fileDTO, File.class);
        file.setFileData(Base64.getDecoder().decode(fileDTO.getFileData()));
        return file;
    }

    private FileDTO convertToFileDTO(File file){
        return modelMapper.map(file, FileDTO.class);
    }
}