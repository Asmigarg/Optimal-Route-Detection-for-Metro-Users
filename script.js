// Metro system data (simplified version of the Java code)
const metro = {
    edges: [
        ["Rithala", "Netaji Subhash Place", 5.2],
        ["Netaji Subhash Place", "Keshav Puram", 1.2],
        ["Keshav Puram", "Kanhaiya Nagar", 0.8],
        ["Kanhaiya Nagar", "Inderlok", 1.2],
        ["Inderlok", "Shastri Nagar", 1.2],
        ["Shastri Nagar", "Pratap Nagar", 1.7],
        ["Pratap Nagar", "Pulbangash", 0.8],
        ["Pulbangash", "Tis Hazari", 0.9],
        ["Tis Hazari", "Kashmere Gate", 1.1],
        ["Kashmere Gate", "Shastri Park", 2.2],
        ["Shastri Park", "Seelampur", 1.6],
        ["Seelampur", "Welcome", 1.1],
        ["Rajouri Garden", "Ramesh Nagar", 1.0],
        ["Ramesh Nagar", "Moti Nagar", 1.2],
        ["Moti Nagar", "Kirti Nagar", 1.0],
        ["Kirti Nagar", "Shadipur", 0.7],
        ["Shadipur", "Patel Nagar", 1.3],
        ["Patel Nagar", "Rajender Place", 0.9],
        ["Rajender Place", "Karol Bagh", 1.0],
        ["Karol Bagh", "Rajiv Chowk", 3.4],
        ["Rajiv Chowk", "Barakhamba Road", 0.7],
        ["Barakhamba Road", "Mandi House", 1.0],
        ["Mandi House", "Pragati Maiden", 0.8],
        ["Pragati Maiden", "Inderprastha", 0.8],
        ["Inderprastha", "Yamuna Bank", 1.8],
        ["Yamuna Bank", "Laxmi Nagar", 1.3],
        ["Laxmi Nagar", "Nirman Vihar", 1.1],
        ["Nirman Vihar", "Preet Vihar", 1.0],
        ["Preet Vihar", "Karkar Duma", 1.2],
        ["Karkar Duma", "Anand Vihar", 1.1],
        ["Anand Vihar", "Kaushambi", 0.8],
        ["Kaushambi", "Vaishali", 1.6],
        ["Yamuna Bank", "Akshardham", 1.3],
        ["Akshardham", "Mayur Vihar Phase-1", 1.8],
        ["Mayur Vihar Phase-1", "Mayur Vihar Extention", 1.2],
        ["Mayur Vihar Extention", "New Ashok Nagar", 0.9],
        ["New Ashok Nagar", "Noida Sector-15", 1.0],
        ["Noida Sector-15", "Noida Sector-16", 1.1],
        ["Noida Sector-16", "Noida Sector-18", 1.1],
        ["Noida Sector-18", "Botanical Garden", 1.1],
        ["Botanical Garden", "Golf Course", 1.2],
        ["Golf Course", "Noida City Center", 1.3],
        ["Madipur", "Shivaji Park", 1.1],
        ["Shivaji Park", "Punjabi Bagh", 1.6],
        ["Punjabi Bagh", "Ashok Park", 0.9],
        ["Ashok Park", "Inderlok", 1.4],
        ["Ashok Park", "Sant Guru Ram Singh Marg", 1.1],
        ["Sant Guru Ram Singh Marg", "Kirti Nagar", 1.0],
        ["Kashmere Gate", "Lal Qila", 1.5],
        ["Lal Qila", "Jama Masjid", 0.8],
        ["Jama Masjid", "Delhi Gate", 1.4],
        ["Delhi Gate", "ITO", 1.3],
        ["ITO", "Mandi House", 0.8],
        ["Mandi House", "Janptah", 1.4],
        ["Janptah", "Central Secretariat", 1.3],
        ["Central Secretariat", "Khan Market", 2.1],
        ["Khan Market", "JL Nehru Stadium", 1.4],
        ["JL Nehru Stadium", "Jangpura", 0.9],
        ["Vishwavidyalaya", "Vidhan Sabha", 1.0],
        ["Vidhan Sabha", "Civil Lines", 1.3],
        ["Civil Lines", "Kashmere Gate", 1.1],
        ["Kashmere Gate", "Chandni Chowk", 1.1],
        ["Chandni Chowk", "Chawri Bazar", 1.0],
        ["Chawri Bazar", "New Delhi", 0.8],
        ["New Delhi", "Rajiv Chowk", 1.1],
        ["Rajiv Chowk", "Patel Chowk", 1.3],
        ["Patel Chowk", "Central Secretariat", 0.9],
        ["Central Secretariat", "Udyog Bhawan", 0.3],
        ["Udyog Bhawan", "Lok Kalyan Marg", 1.6],
        ["Lok Kalyan Marg", "Jor Bagh", 1.2],
        ["Samaypur Badli", "Rohini Sector - 18", 0.8],
        ["Rohini Sector - 18", "Haiderpur Badli Mor", 1.3],
        ["Haiderpur Badli Mor", "Jahangirpuri", 1.3],
        ["Jahangirpuri", "Adarsh Nagar", 1.3],
        ["Adarsh Nagar", "Azadpur", 1.5],
        ["Azadpur", "Model Town", 1.4],
        ["Model Town", "GTB Nagar", 1.4],
        ["GTB Nagar", "Vishwavidyalaya", 0.8],
        ["Jor Bagh", "INA", 1.3],
        ["INA", "AIIMS", 0.8],
        ["AIIMS", "Green Park", 1.0],
        ["Green Park", "Hauz Khas", 1.8],
        ["Hauz Khas", "Malviya Nagar", 1.7],
        ["Malviya Nagar", "Saket", 0.9],
        ["Saket", "Qutab Minar", 1.7],
        ["Qutab Minar", "Chhattarpur", 1.3],
        ["Chhattarpur", "Sultanpur", 1.6],
        ["Sultanpur", "Ghitorni", 1.3],
        ["Ghitorni", "Arjan Garh", 2.7],
        ["Arjan Garh", "Guru Dronacharya", 2.3],
        ["Guru Dronacharya", "Sikandarpur", 1.0],
        ["Sikandarpur", "MG Road", 0.9],
        ["MG Road", "IFFCO Chowk", 1.5],
        ["IFFCO Chowk", "Huda City Centre", 1.5]
    ]
};

// Helper function to get all unique stations
function getAllStations() {
    const stations = new Set();
    metro.edges.forEach(edge => {
        stations.add(edge[0]);
        stations.add(edge[1]);
    });
    return Array.from(stations).sort();
}

// Populate dropdowns
function populateDropdowns() {
    const stations = getAllStations();
    const sourceSelect = document.getElementById('source');
    const destinationSelect = document.getElementById('destination');

    stations.forEach(station => {
        sourceSelect.add(new Option(station, station));
        destinationSelect.add(new Option(station, station));
    });
}

// Dijkstra's algorithm (simplified)
function dijkstra(start, end) {
    const stations = getAllStations();
    const distances = {};
    const previous = {};
    const unvisited = new Set(stations);

    stations.forEach(station => {
        distances[station] = Infinity;
        previous[station] = null;
    });
    distances[start] = 0;

    while (unvisited.size > 0) {
        const current = Array.from(unvisited).reduce((a, b) => distances[a] < distances[b] ? a : b);

        if (current === end) break;

        unvisited.delete(current);

        for (const [from, to, distance] of metro.edges) {
            if (from === current || to === current) {
                const other = from === current ? to : from;
                const newDistance = distances[current] + distance;
                if (newDistance < distances[other]) {
                    distances[other] = newDistance;
                    previous[other] = current;
                }
            }
        }
    }

    const path = [];
    let current = end;
    while (current !== null) {
        path.unshift(current);
        current = previous[current];
    }

    return { distance: distances[end], path };
}

// Calculate fare (simplified)
function calculateFare(distance) {
    if (distance <= 2) return 10;
    if (distance <= 5) return 20;
    if (distance <= 12) return 30;
    if (distance <= 21) return 40;
    if (distance <= 32) return 50;
    return 60;
}

// Estimate travel time (simplified)
function estimateTravelTime(distance) {
    const averageSpeed = 35; // km/h
    return Math.round((distance / averageSpeed) * 60); // minutes
}

// Create SVG map
function createMap(path) {
    const mapContainer = document.getElementById('map');
    mapContainer.innerHTML = '';
    const draw = SVG().addTo(mapContainer).size('100%', '100%');

    const stations = path.map((station, index) => ({
        x: 50 + (index * 100),
        y: 150,
        name: station
    }));

    // Draw connections
    for (let i = 0; i < stations.length - 1; i++) {
        draw.line(stations[i].x, stations[i].y, stations[i+1].x, stations[i+1].y)
            .stroke({ color: '#5cb85c', width: 2, linecap: 'round' });
    }

    // Draw stations
    stations.forEach((station, index) => {
        draw.circle(10).fill('#5cb85c').center(station.x, station.y);
        draw.text(station.name).font({ size: 12, family: 'Arial' }).center(station.x, station.y + 20);
    });
}

// Event listener for calculate button
document.getElementById('calculate').addEventListener('click', () => {
    const source = document.getElementById('source').value;
    const destination = document.getElementById('destination').value;

    if (source === destination) {
        alert("Source and destination cannot be the same!");
        return;
    }

    const result = dijkstra(source, destination);
    const fare = calculateFare(result.distance);
    const time = estimateTravelTime(result.distance);

    document.getElementById('distance').textContent = result.distance.toFixed(2);
    document.getElementById('fare').textContent = fare;
    document.getElementById('time').textContent = time;

    const stationsList = document.getElementById('stations');
    stationsList.innerHTML = '';
    result.path.forEach((station, index) => {
        const li = document.createElement('li');
        li.textContent = station;
        li.classList.add('fade-in');
        li.style.animationDelay = `${index * 0.1}s`;
        stationsList.appendChild(li);
    });

    document.getElementById('result').classList.remove('hidden');
    document.getElementById('map-container').classList.remove('hidden');
    createMap(result.path);
});

// Active link highlighting
const navLinks = document.querySelectorAll('.nav-links a');
navLinks.forEach(link => {
    link.addEventListener('click', function() {
        navLinks.forEach(l => l.classList.remove('active'));
        this.classList.add('active');
    });
});

// Smooth scrolling for navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const targetId = this.getAttribute('href').substring(1);
        const targetElement = document.getElementById(targetId);
        
        if (targetElement) {
            targetElement.scrollIntoView({
                behavior: 'smooth'
            });
            
            // Update active class
            document.querySelectorAll('.nav-links a').forEach(link => {
                link.classList.remove('active');
            });
            this.classList.add('active');
        }
    });
});

// Add this function to handle scroll events and update active link
function updateActiveLink() {
    const scrollPosition = window.scrollY;
    
    document.querySelectorAll('section, #home').forEach(section => {
        const sectionTop = section.offsetTop - 100;
        const sectionBottom = sectionTop + section.offsetHeight;
        const sectionId = section.getAttribute('id');
        
        if (scrollPosition >= sectionTop && scrollPosition < sectionBottom) {
            document.querySelectorAll('.nav-links a').forEach(link => {
                link.classList.remove('active');
                if (link.getAttribute('href') === `#${sectionId}`) {
                    link.classList.add('active');
                }
            });
        }
    });
}

// Add scroll event listener
window.addEventListener('scroll', updateActiveLink);

// Initialize the page
populateDropdowns();
updateActiveLink();

// Contact form submission
const contactForm = document.getElementById('contact-form');
contactForm.addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;
    
    // Here you would typically send this data to a server
    // For now, we'll just log it to the console
    console.log('Form submitted:', { name, email, message });
    
    // Clear the form
    contactForm.reset();
    
    // Show a success message (you can replace this with a more user-friendly notification)
    alert('Thank you for your message. We will get back to you soon!');
});

// Make sure to call updateActiveLink on page load and scroll
window.addEventListener('load', updateActiveLink);

