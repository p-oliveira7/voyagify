package voyagify.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voyagify.api.domain.review.ReviewDataDTO;
import voyagify.api.domain.review.ReviewService;
import voyagify.api.domain.review.ReviewInputDTO;
import voyagify.api.domain.review.ReviewRepository;

import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity postReview(@RequestBody @Valid ReviewInputDTO data) {
        var dto = reviewService.create(data);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/random")
    public List<ReviewDataDTO> getRandomReviews() {
        return reviewService.getRandomReviews();
    }
}
