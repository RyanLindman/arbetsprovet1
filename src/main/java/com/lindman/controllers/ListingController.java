package com.lindman.controllers;

import com.lindman.models.ApplicationUser;
import com.lindman.models.Listing;
import com.lindman.repository.ListingRepository;
import com.lindman.repository.UserRepository;
import com.lindman.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/listings")
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Listing>> getListingById(@PathVariable("id") Long id) {
        Optional<Listing> listing = listingRepository.findById(id);
        return new ResponseEntity<>(listing, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Listing> createListing(@RequestBody Listing newListing, @AuthenticationPrincipal ApplicationUser user) {


        newListing.setUser(user);

        Listing listing = listingRepository.save(newListing);

        return new ResponseEntity<>(newListing, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Listing> deleteListing(@PathVariable("id") Long id) {
        listingRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Listing> updateListing(@RequestBody Listing updatedListing) {
        Listing listing = listingRepository.save(updatedListing);
        return new ResponseEntity<>(listing, HttpStatus.CREATED);
    }

    @GetMapping("/filter1")
    public ResponseEntity<List<Listing>> searchListingByTitleOrDescription(String title, String description) {

        List<Listing> listing = listingRepository.findByTitleOrDescription(title, description);

        return new ResponseEntity<>(listing, HttpStatus.OK);
    }

    @GetMapping("/filter2")
    public ResponseEntity<List<Listing>> searchListingByDate(Date searchDate) {

        List<Listing> listings = listingRepository.findByDate(searchDate);


        return new ResponseEntity<>(listings, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Listing>> getAllListingsInEnglish( /*@RequestParam(name = "lang") String language*/) throws IOException, InterruptedException {
        String key = System.getenv("RAPID_API");

      /**  HttpRequest request = HttpRequest.newBuilder()



                .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", key)
                .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("source_language=sv&target_language="+ language + "&text=solen"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
       */


        List<Listing> listings = listingRepository.findAll();

        return new ResponseEntity<>(listings, HttpStatus.OK);


    }
}
