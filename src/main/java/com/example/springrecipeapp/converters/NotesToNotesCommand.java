package com.example.springrecipeapp.converters;

import com.example.springrecipeapp.commands.NotesCommand;
import com.example.springrecipeapp.models.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Override
    public NotesCommand convert(Notes source) {

        if (source == null){
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setNotes(source.getNotes());
        return notesCommand;
    }
}




