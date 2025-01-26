package org.example.crudspringfjv.ui.controllers;

import org.example.crudspringfjv.domain.Cancion;
import org.example.crudspringfjv.service.PlaylistService;
import org.example.crudspringfjv.utils.Constantes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlist")
    public String getPlaylist(Model model) {
        model.addAttribute("pl", playlistService.getAll());
        return "playlist";
    }
    @PostMapping("/playlist")
    public String managePlaylist(@RequestParam String action,
                                 @RequestParam(required = false) String titulo,
                                 @RequestParam(required = false) String artista,
                                 @RequestParam(required = false) Integer id) {
        if (Constantes.ACTION_ADD.equals(action)) {
            playlistService.add(new Cancion(playlistService.getAll().size() + 1, titulo, artista));
        } else if (Constantes.ACTION_DELETE.equals(action) && id != null) {
            playlistService.delete(id);
        } else if (Constantes.ACTION_UPDATE.equals(action) && id != null) {
            playlistService.update(new Cancion(id, titulo, artista));
        }
        return Constantes.PLAYLIST_VIEW;
    }
}