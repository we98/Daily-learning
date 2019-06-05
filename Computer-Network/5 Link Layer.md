## Link Layer
1. Introduction
    1. Link layer has the responsibility of transferring datagram from one node to physically adjacent node over a link
    2. In each and every host, link layer implemented in "adapter" or on a chip
    3. 
***
2. The services link layer provides
    1. Framing: encapsulate datagram into frame, adding header, tailer
    2. Link address: provide channel access if shared medium
    3. Reliable delivery between adjacent nodes: seldom used on low bit-error link, often used in wireless links with high error rates
    4. Flow control: pacing between adjacent sending and receiving nodes
    5. error dection: errors caused by signal attenuation, noise
    6. error correction: receiver identifies and corrects bit error(s) without resorting to retransmission
    7. half-duplex and full-duplex: with half duplex, nodes at both ends of link can transmit, but not at same time
***
3. Error detection
    1. EDC: Error detection and correction bits
    2. Not 100% reliable, may miss some errors, but rarely, larger EDC fields yield better detection and correction
    3. Error detection methods
    - Two-dimensional parity checking detect and check single bit error, detect two-bit error, cannot detect three-bit or more error
    - Internet checksum, treat segment contents as sequence of 16-bit integers
    - Cyclic redundancy check, CRC, R=remainer(D*(2^r)/G)
***
4. Multiple access links and protocols
    1. Two type of links
    - point-to-point
    - broadcast
    2. multiple access protocol(distributed algorithm that determines how nodes share channel)
    - channel partitioning
    - random access
    - "talking turns"
    