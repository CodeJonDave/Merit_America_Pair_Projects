package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }


    @GetMapping(path = "")
    public List<CatCard> getAllCards() {
        return catCardDao.getCatCards();
    }

    @GetMapping(path = "/{id}")
    public CatCard getCardByID(@PathVariable int id) {
        return catCardDao.getCatCardById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public CatCard saveCatCard(@Valid @RequestBody CatCard catCard) {
        return catCardDao.createCatCard(catCard);
    }

    @PutMapping(path = "/{id}")
    public CatCard updateCard(@PathVariable int id,@Valid @RequestBody CatCard catCard) {
        catCard.setCatCardId(id);
        return catCardDao.updateCatCard(catCard);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void deleteCard(@PathVariable int id) {
        int numberOfRows = catCardDao.deleteCatCardById(id);
        if (numberOfRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CatCard not found");
        }
    }

    @GetMapping("/random")
    public CatCard getRandom(){
        CatCard newCard = new CatCard();
        CatFact catFact = catFactService.getFact();
        CatPic catPic = catPicService.getPic();

        newCard.setCatFact(catFact.getText());
        newCard.setImgUrl(catPic.getFile());
        newCard.setCaption("Type Your Caption Here");

        return newCard;
    }

}
