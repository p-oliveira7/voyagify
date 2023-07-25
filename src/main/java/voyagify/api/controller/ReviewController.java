package voyagify.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voyagify.api.domain.review.Review;
import voyagify.api.domain.review.ReviewInputDTO;
import voyagify.api.domain.review.ReviewRepository;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity postReview(@RequestBody @Valid ReviewInputDTO review){
        return null;
    }
}
